document.addEventListener("DOMContentLoaded", () => {
document.getElementById("departman").addEventListener("change", function () {
  const departman = this.value;
  const doktorSelect = document.getElementById("doktorAdi");

  // Seçimi temizle
  doktorSelect.innerHTML = '<option value="">Doktor Seçiniz</option>';

  if (departman) {
    fetch(`http://localhost:8080/api/doctors/by-departman/${departman}`)
      .then(response => response.json())
      .then(data => {
        data.forEach(doktor => {
          const option = document.createElement("option");
          option.value = doktor.ad;
          option.textContent = doktor.ad;
          doktorSelect.appendChild(option);
        });
      })
      .catch(err => {
        console.error("Doktorlar yüklenemedi:", err);
      });
  }
});

  const hastaId = localStorage.getItem("hastaId");
  console.log("Hasta ID:", hastaId); // kontrol

  if (!hastaId) {
    alert("Giriş yapılmamış. Lütfen tekrar giriş yapın.");
    window.location.href = "index.html";
    return;
  }

  const tableBody = document.querySelector("#appointmentsTable tbody");
  const form = document.getElementById("appointmentForm");
  const message = document.getElementById("message");

  // Randevuları Getir
  fetch(`http://localhost:8080/api/appointments/${hastaId}`)
    .then((res) => res.json())
    .then((appointments) => {
      if (!Array.isArray(appointments)) {
        console.error("Gelen veri dizi değil:", appointments);
        message.textContent = "Randevular alınamadı.";
        return;
      }

      appointments.forEach((appt) => {
        const row = document.createElement("tr");

        row.innerHTML = `
          <td>${appt.randevuTarihi}</td>
          <td>${appt.doktorAdi}</td>
          <td>${appt.departman}</td>
          <td>${appt.notlar || ""}</td>
          <td><button onclick="sil(${appt.id})">Sil</button></td>
        `;

        tableBody.appendChild(row);
      });
    })
    .catch((err) => {
      message.textContent = "Randevular yüklenemedi.";
      console.error(err);
    });

  // Randevu Ekle
  form.addEventListener("submit", async (e) => {
    e.preventDefault();

    const departman = document.getElementById("departman").value;
    const doktorAdi = document.getElementById("doktorAdi").value;
    const randevuTarihi = document.getElementById("randevuTarihi").value;
    const notlar = document.getElementById("notlar").value;
    
    
    function isValidAppointmentTime(datetimeString) {
    const selectedDate = new Date(datetimeString);
    const hour = selectedDate.getHours();
    return hour >= 8 && hour <= 17;
    }

// Randevu oluşturma fonksiyonunun içinde, veriyi göndermeden önce:
if (!isValidAppointmentTime(randevuTarihi)) {
    alert("Randevu saati 08:00 ile 17:00 arasında olmalıdır.");
    return;
}

    const yeniRandevu = {
      hastaId: Number(hastaId),
      departman,
      doktorAdi,
      randevuTarihi,
      notlar,
    };

    console.log("POST edilen veri:", yeniRandevu);

    try {
      const res = await fetch("http://localhost:8080/api/appointments", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(yeniRandevu),
      });

      if (!res.ok) {
        const text = await res.text();
        message.textContent = text;
      } else {
        location.reload(); // başarılıysa sayfayı yenile
      }
    } catch (error) {
      console.error(error);
      message.textContent = "Randevu oluşturulamadı.";
    }
  });
});

// Randevu Silme
function sil(id) {
  if (!confirm("Bu randevuyu silmek istediğinize emin misiniz?")) return;

  fetch(`http://localhost:8080/api/appointments/${id}`, {
    method: "DELETE",
  })
    .then(() => location.reload())
    .catch((err) => {
      console.error(err);
      alert("Silme işlemi başarısız.");
    });
}
