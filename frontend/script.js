const form = document.getElementById("loginForm");
const errorText = document.getElementById("error");

form.addEventListener("submit", async (e) => {
  e.preventDefault();
  const tc = document.getElementById("tc").value.trim();
  const password = document.getElementById("password").value.trim();

  if (!tc || !password) {
    errorText.textContent = "TC Kimlik No ve şifre boş bırakılamaz.";
    return;
  }

  try {
    const response = await fetch(`http://localhost:8080/api/patients/${tc}`);
    if (response.ok) {
      const patient = await response.json();
      if (patient && patient.sifre === password) {
         localStorage.setItem("hastaId", patient.id); // hasta ID'yi sakla
         localStorage.setItem("tc", tc); // istersen tc'yi de tutabilirsin
         window.location.href = "randevu.html";
      } else {
        errorText.textContent = "Şifre hatalı!";
      }
    } else if (response.status === 404) {
      // Hasta yoksa yeni kayıt
      const newPatient = {
        tcKimlikNo: tc,
        adSoyad: "Yeni Hasta",
        dogumTarihi: "",
        telefon: "",
        sifre: password
      };

const saveResponse = await fetch("http://localhost:8080/api/patients/login", {
  method: "POST",
  headers: { "Content-Type": "application/json" },
  body: JSON.stringify(newPatient)
});

if (saveResponse.ok) {
  const createdPatient = await saveResponse.json();
  localStorage.setItem("hastaId", createdPatient.id);
  localStorage.setItem("tc", createdPatient.tcKimlikNo);
  window.location.href = "randevu.html";
} else {
  errorText.textContent = "Kayıt oluşturulamadı.";
}

    } else {
      errorText.textContent = "Sunucu hatası.";
    }
  } catch (err) {
    console.error(err);
    errorText.textContent = "Bağlantı hatası.";
  }
});
