// src/Login.js
import { useNavigate } from "react-router-dom";
import { useState } from "react";

export default function Login() {
  const navigate = useNavigate();
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = async () => {
    try {
      const res = await fetch("http://10.153.43.8/api/auth/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",   // 👈 send JSON
          "Accept": "application/json"          // 👈 expect JSON back
        },
        body: JSON.stringify({ username, password }),
        credentials: "include"  // 👈 if using cookies/session
      });

      if (!res.ok) {
        alert("Invalid credentials");
        return;
      }

      const data = await res.json();
      console.log("Login success:", data);

      // ✅ Save token in localStorage (if JWT based)
      if (data.token) {
        localStorage.setItem("token", data.token);
      }

      // ✅ Redirect to dashboard
      navigate("/dashboard");
    } catch (err) {
      console.error("Login failed:", err);
      alert("Login request failed.");
    }
  };

  return (
    <div style={{ padding: "20px" }}>
      <h2>Login</h2>
      <input
        placeholder="Username"
        value={username}
        onChange={(e) => setUsername(e.target.value)}
      />
      <br />
      <input
        placeholder="Password"
        type="password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
      />
      <br />
      <button onClick={handleLogin}>Login</button>
    </div>
  );
}
