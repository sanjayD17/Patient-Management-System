// auth.js
(() => {
  const API_GATEWAY = "http://localhost:8000"; // your gateway
  const authForm = document.getElementById("auth-form");
  const toggleLink = document.getElementById("toggle-link");
  const formTitle = document.getElementById("form-title");
  const submitBtn = document.getElementById("submit-btn");
  const roleGroup = document.getElementById("role-group");
  const responseMsg = document.getElementById("response-msg");

  let isLogin = true;

  toggleLink.addEventListener("click", () => {
    isLogin = !isLogin;
    formTitle.textContent = isLogin ? "Login" : "Register";
    submitBtn.textContent = isLogin ? "Login" : "Register";
    roleGroup.style.display = isLogin ? "none" : "block";
    responseMsg.textContent = "";
    responseMsg.style.color = "";
   // toggle helper rows
  document.getElementById("back-to-login").style.display = isLogin ? "none" : "flex";
});

// handle <-- Login button
document.getElementById("back-link").addEventListener("click", () => {
  isLogin = true;
  formTitle.textContent = "Login";
  submitBtn.textContent = "Login";
  roleGroup.style.display = "none";
  responseMsg.textContent = "";
  document.getElementById("back-to-login").style.display = "none";
});

  // helper to parse text or json
  async function parseResponse(res) {
    const ct = res.headers.get("content-type") || "";
    if (ct.includes("application/json")) {
      return res.json();
    }
    return res.text();
  }

  authForm.addEventListener("submit", async (e) => {
    e.preventDefault();
    responseMsg.style.color = "#ff9aa2";
    responseMsg.textContent = "";

    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value.trim();
    const role = document.getElementById("role") ? document.getElementById("role").value : "USER";

    if (!email || !password) {
      responseMsg.textContent = "Please fill required fields.";
      return;
    }
    if (password.length < 8) {
      responseMsg.textContent = "Password must be at least 8 characters.";
      return;
    }

    const url = isLogin ? `${API_GATEWAY}/auth/login` : `${API_GATEWAY}/auth/register`;
    const payload = isLogin ? { email, password } : { email, password, role };

    try {
      const res = await fetch(url, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(payload)
      });

      const body = await parseResponse(res);

      if (!res.ok) {
        if (typeof body === "string") {
          responseMsg.textContent = body || "Request failed";
        } else if (body && body.message) {
          responseMsg.textContent = body.message;
        } else {
          responseMsg.textContent = "Authentication failed";
        }
        return;
      }

      if (isLogin) {
        const token = (typeof body === "object" && body.token) ? body.token : null;
        if (!token) {
          responseMsg.textContent = "Login succeeded but token not returned.";
          return;
        }
        localStorage.setItem("jwtToken", token);
        localStorage.setItem("userEmail", email);
        window.location.href = "dashboard.html";
      } else {
        responseMsg.style.color = "#2cc981";
        responseMsg.textContent =
          (typeof body === "string") ? body : (body.message || "Registered successfully. Please login.");
        isLogin = true;
        formTitle.textContent = "Login";
        submitBtn.textContent = "Login";
        roleGroup.style.display = "none";
      }
    } catch (err) {
      console.error(err);
      responseMsg.textContent = "Network error. Is backend running?";
    }
  });

  // auto-redirect if already logged in
  (function checkToken() {
    const token = localStorage.getItem("jwtToken");
    if (token) {
      window.location.href = "dashboard.html";
    }
  })();
})();
