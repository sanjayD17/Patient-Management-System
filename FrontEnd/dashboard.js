

// (() => {
//   const API_GATEWAY = "http://localhost:8000";
//   const token = localStorage.getItem("jwtToken");
//   const userEmail = localStorage.getItem("userEmail") || "";
//   const userRoleEl = document.getElementById("user-role");
//   const logoutBtn = document.getElementById("logout-btn");

  // Guard
  // if (!token) {
  //   window.location.href = "index.html";
  //   throw new Error("Not authenticated");
  // }

  // userRoleEl.textContent = userEmail;
  // logoutBtn.addEventListener("click", () => {
  //   localStorage.removeItem("jwtToken");
  //   localStorage.removeItem("userEmail");
  //   window.location.href = "index.html";
  // });

  // UI elements
  // const addBtn = document.getElementById("add-patient-btn");
  // const searchInput = document.getElementById("search-input");
  // const patientsGrid = document.getElementById("patients-grid");
  // const noResults = document.getElementById("no-results");

  // modal elements
  // const modal = document.getElementById("patient-modal");
  // const modalTitle = document.getElementById("modal-title");
  // const closeModalBtns = document.querySelectorAll("#close-modal");
  // const cancelBtn = document.getElementById("cancel-btn");
  // const patientForm = document.getElementById("patient-form");
  // const modalResponse = document.getElementById("modal-response-msg");

  // const inputId = document.getElementById("patient-id");
  // const inputName = document.getElementById("patient-name");
  // const inputEmail = document.getElementById("patient-email");
  // const inputAddress = document.getElementById("patient-address");
  // const inputDob = document.getElementById("patient-dob");
  // const inputRegistered = document.getElementById("patient-registered-date");

  // let patients = []; // cache

  // helpers
  // function headers() {
  //   return {
  //     "Content-Type": "application/json",
  //     "Authorization": `Bearer ${token}`
  //   };
  // }

  // function showModal(open = true) {
  //   modal.setAttribute("aria-hidden", open ? "false" : "true");
  //   if (open) {
  //     modalResponse.textContent = "";
  //     document.body.classList.add("modal-open");
  //   } else {
  //     document.body.classList.remove("modal-open");
  //   }
  // }

  // function resetForm() {
  //   inputId.value = "";
  //   inputName.value = "";
  //   inputEmail.value = "";
  //   inputAddress.value = "";
  //   inputDob.value = "";
  //   inputRegistered.value = "";
  //   modalResponse.textContent = "";
  // }

  // async function fetchPatients() {
  //   try {
  //     const res = await fetch(`${API_GATEWAY}/api/patients/get`, {
  //       method: "GET",
  //       headers: headers()
  //     });
  //     if (!res.ok) {
  //       patients = [];
  //       renderPatients();
  //       return;
  //     }
  //     const body = await res.json();
  //     patients = Array.isArray(body.data) ? body.data : (body.data || []);
  //     renderPatients();
  //   } catch (err) {
  //     console.error(err);
  //     patients = [];
  //     renderPatients();
  //   }
  // }

  // function renderPatients(filter = "") {
  //   patientsGrid.innerHTML = "";
  //   const normalizedFilter = filter.trim().toLowerCase();
  //   const filtered = patients.filter(p => {
  //     if (!normalizedFilter) return true;
  //     return (p.name && p.name.toLowerCase().includes(normalizedFilter)) ||
  //            (p.email && p.email.toLowerCase().includes(normalizedFilter));
  //   });

  //   if (!filtered.length) {
  //     noResults.style.display = "block";
  //     return;
  //   } else {
  //     noResults.style.display = "none";
  //   }

  //   filtered.forEach(p => {
  //     const card = document.createElement("div");
  //     card.className = "patient-card";

  //     const initials = p.name ? p.name.split(" ").map(n => n[0].toUpperCase()).join("") : "👤";

  //     card.innerHTML = `
  //       <div class="card-inner">
  //         <!-- FRONT -->
  //         <div class="card-front">
  //           <div class="avatar">${initials}</div>
  //           <h3>${p.name || "-"}</h3>
  //           <p>${p.email || "-"}</p>
  //         </div>

  //         <!-- BACK -->
  //         <div class="card-back">
  //           <p><strong>Address:</strong> ${p.address || "-"}</p>
  //           <p><strong>DOB:</strong> ${p.dob || "-"}</p>
  //           <p><strong>Registered:</strong> ${p.registeredDate || "-"}</p>
  //           <div class="card-actions">
  //             <button class="action-btn action-edit">✏ Edit</button>
  //             <button class="action-btn action-delete">🗑 Delete</button>
  //           </div>
  //         </div>
  //       </div>
  //     `;

      // Edit button
      // card.querySelector(".action-edit").addEventListener("click", (e) => {
      //   e.stopPropagation();
      //   openEditModal(p);
      // });

      // Delete button
  //     card.querySelector(".action-delete").addEventListener("click", (e) => {
  //       e.stopPropagation();
  //       deletePatient(p.id);
  //     });

  //     patientsGrid.appendChild(card);
  //   });
  // }

  // Open modal for Add
  // function openAddModal() {
  //   resetForm();
  //   modalTitle.textContent = "Add Patient";
  //   showModal(true);
  // }

  // Open modal for Edit
  // function openEditModal(p) {
  //   resetForm();
  //   modalTitle.textContent = "Edit Patient";
  //   inputId.value = p.id || "";
  //   inputName.value = p.name || "";
  //   inputEmail.value = p.email || "";
  //   inputAddress.value = p.address || "";
  //   inputDob.value = p.dob || "";
  //   inputRegistered.value = p.registeredDate || "";
  //   showModal(true);
  // }

  // Save (Add or Update)
  // patientForm.addEventListener("submit", async (e) => {
  //   e.preventDefault();
  //   const patient = {
  //     id: inputId.value,
  //     name: inputName.value,
  //     email: inputEmail.value,
  //     address: inputAddress.value,
  //     dob: inputDob.value,
  //     registeredDate: inputRegistered.value
  //   };

  //   const url = patient.id
  //     ? `${API_GATEWAY}/api/patients/update/${patient.id}`
  //     : `${API_GATEWAY}/api/patients/add`;

  //   const method = patient.id ? "PUT" : "POST";

  //   try {
  //     const res = await fetch(url, {
  //       method,
  //       headers: headers(),
  //       body: JSON.stringify(patient)
  //     });

  //     if (!res.ok) throw new Error("Failed to save");
  //     modalResponse.textContent = "Saved successfully ✅";
  //     fetchPatients();
  //     setTimeout(() => showModal(false), 800);
  //   } catch (err) {
  //     modalResponse.textContent = "Error ❌ " + err.message;
  //   }
  // });

  // Delete
  // async function deletePatient(id) {
  //   if (!confirm("Are you sure to delete this patient?")) return;
  //   try {
  //     const res = await fetch(`${API_GATEWAY}/api/patients/delete/${id}`, {
  //       method: "DELETE",
  //       headers: headers()
  //     });
  //     if (!res.ok) throw new Error("Failed to delete");
  //     fetchPatients();
  //   } catch (err) {
  //     alert("Delete error ❌: " + err.message);
  //   }
  // }

  // Event listeners
  // addBtn.addEventListener("click", openAddModal);
  // cancelBtn.addEventListener("click", () => showModal(false));
  // closeModalBtns.forEach(btn => btn.addEventListener("click", () => showModal(false)));
  // searchInput.addEventListener("input", (e) => renderPatients(e.target.value));

  // // Start
//   fetchPatients();
// })();



(() => {
  const API_GATEWAY = "http://localhost:8000";
  const token = localStorage.getItem("jwtToken");
  const userEmail = localStorage.getItem("userEmail") || "";
  const userRoleEl = document.getElementById("user-role");
  const logoutBtn = document.getElementById("logout-btn");

  // Guard
  if (!token) {
    window.location.href = "index.html";
    throw new Error("Not authenticated");
  }

  userRoleEl.textContent = userEmail;
  logoutBtn.addEventListener("click", () => {
    localStorage.removeItem("jwtToken");
    localStorage.removeItem("userEmail");
    window.location.href = "index.html";
  });

  // Sidebar navigation
  const navLinks = document.querySelectorAll(".nav-link");
  const contentHeader = document.querySelector(".content-header h1");
  const contentSub = document.querySelector(".content-header p");
  const patientsGrid = document.getElementById("patients-grid");
  const noResults = document.getElementById("no-results");
  const addBtn = document.getElementById("add-patient-btn");
  const searchInput = document.getElementById("search-input");

  // Modal elements
  const modal = document.getElementById("patient-modal");
  const modalTitle = document.getElementById("modal-title");
  const closeModalBtns = document.querySelectorAll("#close-modal");
  const cancelBtn = document.getElementById("cancel-btn");
  const patientForm = document.getElementById("patient-form");
  const modalResponse = document.getElementById("modal-response-msg");

  const inputId = document.getElementById("patient-id");
  const inputName = document.getElementById("patient-name");
  const inputEmail = document.getElementById("patient-email");
  const inputAddress = document.getElementById("patient-address");
  const inputDob = document.getElementById("patient-dob");
  const inputRegistered = document.getElementById("patient-registered-date");

  let patients = []; // cache

  // Helpers
  function headers() {
    return {
      "Content-Type": "application/json",
      "Authorization": `Bearer ${token}`
    };
  }

  function showModal(open = true) {
    modal.setAttribute("aria-hidden", open ? "false" : "true");
    if (open) {
      modalResponse.textContent = "";
      document.body.classList.add("modal-open");
    } else {
      document.body.classList.remove("modal-open");
    }
  }

  function resetForm() {
    inputId.value = "";
    inputName.value = "";
    inputEmail.value = "";
    inputAddress.value = "";
    inputDob.value = "";
    inputRegistered.value = "";
    modalResponse.textContent = "";
  }

  async function fetchPatients() {
    try {
      const res = await fetch(`${API_GATEWAY}/api/patients/get`, {
        method: "GET",
        headers: headers()
      });
      if (!res.ok) {
        patients = [];
        renderPatients();
        return;
      }
      const body = await res.json();
      patients = Array.isArray(body.data) ? body.data : (body.data || []);
      renderPatients();
    } catch (err) {
      console.error(err);
      patients = [];
      renderPatients();
    }
  }

  function renderPatients(filter = "") {
    patientsGrid.innerHTML = "";
    const normalizedFilter = filter.trim().toLowerCase();
    const filtered = patients.filter(p => {
      if (!normalizedFilter) return true;
      return (p.name && p.name.toLowerCase().includes(normalizedFilter)) ||
             (p.email && p.email.toLowerCase().includes(normalizedFilter));
    });

    if (!filtered.length) {
      noResults.style.display = "block";
      return;
    } else {
      noResults.style.display = "none";
    }

    filtered.forEach(p => {
      const card = document.createElement("div");
      card.className = "patient-card";

      const initials = p.name ? p.name.split(" ").map(n => n[0].toUpperCase()).join("") : "👤";

      card.innerHTML = `
        <div class="card-inner">
          <!-- FRONT -->
          <div class="card-front">
            <div class="avatar">${initials}</div>
            <h3>${p.name || "-"}</h3>
            <p>${p.email || "-"}</p>
          </div>

          <!-- BACK -->
          <div class="card-back">
            <p><strong>Address:</strong> ${p.address || "-"}</p>
            <p><strong>DOB:</strong> ${p.dob || "-"}</p>
            <p><strong>Registered:</strong> ${p.registeredDate || "-"}</p>
            <div class="card-actions">
              <button class="action-btn action-edit">✏ Edit</button>
              <button class="action-btn action-delete">🗑 Delete</button>
            </div>
          </div>
        </div>
      `;

      // Edit button
      card.querySelector(".action-edit").addEventListener("click", (e) => {
        e.stopPropagation();
        openEditModal(p);
      });

      // Delete button
      card.querySelector(".action-delete").addEventListener("click", (e) => {
        e.stopPropagation();
        deletePatient(p.id);
      });

      patientsGrid.appendChild(card);
    });
  }

  // Open modal for Add
  function openAddModal() {
    resetForm();
    modalTitle.textContent = "Add Patient";
    showModal(true);
  }

  // Open modal for Edit
  function openEditModal(p) {
    resetForm();
    modalTitle.textContent = "Edit Patient";
    inputId.value = p.id || "";
    inputName.value = p.name || "";
    inputEmail.value = p.email || "";
    inputAddress.value = p.address || "";
    inputDob.value = p.dob || "";
    inputRegistered.value = p.registeredDate || "";
    showModal(true);
  }

  // Save (Add or Update)
  patientForm.addEventListener("submit", async (e) => {
    e.preventDefault();
    const patient = {
      id: inputId.value,
      name: inputName.value,
      email: inputEmail.value,
      address: inputAddress.value,
      dob: inputDob.value,
      registeredDate: inputRegistered.value
    };

    const url = patient.id
      ? `${API_GATEWAY}/api/patients/update/${patient.id}`
      : `${API_GATEWAY}/api/patients/add`;

    const method = patient.id ? "PUT" : "POST";

    try {
      const res = await fetch(url, {
        method,
        headers: headers(),
        body: JSON.stringify(patient)
      });

      if (!res.ok) throw new Error("Failed to save");
      modalResponse.textContent = "Saved successfully ✅";
      fetchPatients();
      setTimeout(() => showModal(false), 800);
    } catch (err) {
      modalResponse.textContent = "Error ❌ " + err.message;
    }
  });

  // Delete
  async function deletePatient(id) {
    if (!confirm("Are you sure to delete this patient?")) return;
    try {
      const res = await fetch(`${API_GATEWAY}/api/patients/delete/${id}`, {
        method: "DELETE",
        headers: headers()
      });
      if (!res.ok) throw new Error("Failed to delete");
      fetchPatients();
    } catch (err) {
      alert("Delete error ❌: " + err.message);
    }
  }

  // ------------------ Billing & Analytics ------------------
  function showBilling() {
    contentHeader.textContent = "Billing";
    contentSub.textContent = "This is a dummy billing dashboard. Replace with real content later.";
    patientsGrid.innerHTML = `
      <div class="empty">
        <div class="empty-emoji">💳</div>
        <div class="empty-title">Billing Dashboard</div>
        <div class="empty-sub">Replace with real billing content.</div>
      </div>
    `;
    addBtn.style.display = "none";
    searchInput.style.display = "none";
  }

  function showAnalytics() {
    contentHeader.textContent = "Analytics";
    contentSub.textContent = "This is a dummy analytics dashboard. Replace with real stats later.";
    patientsGrid.innerHTML = `
      <div class="empty">
        <div class="empty-emoji">📊</div>
        <div class="empty-title">Analytics Dashboard</div>
        <div class="empty-sub">Replace with real analytics charts.</div>
      </div>
    `;
    addBtn.style.display = "none";
    searchInput.style.display = "none";
  }

  function showPatientsView() {
    contentHeader.textContent = "Patients";
    contentSub.textContent = "Manage patient records, add new patients, and update details.";
    addBtn.style.display = "inline-block";
    searchInput.style.display = "inline-block";
    fetchPatients();
  }

  // Sidebar navigation event
  navLinks.forEach(link => {
    link.addEventListener("click", (e) => {
      e.preventDefault();
      navLinks.forEach(l => l.classList.remove("active"));
      link.classList.add("active");

      const text = link.textContent.trim().toLowerCase();
      if (text === "patients") showPatientsView();
      else if (text === "billing") showBilling();
      else if (text === "analytics") showAnalytics();
      else {
        patientsGrid.innerHTML = `<p>Coming soon...</p>`;
        addBtn.style.display = "none";
        searchInput.style.display = "none";
      }
    });
  });

  // Modal events
  addBtn.addEventListener("click", openAddModal);
  cancelBtn.addEventListener("click", () => showModal(false));
  closeModalBtns.forEach(btn => btn.addEventListener("click", () => showModal(false)));
  searchInput.addEventListener("input", (e) => renderPatients(e.target.value));

  // Initialize
  showPatientsView();
})();
