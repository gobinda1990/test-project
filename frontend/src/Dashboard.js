// src/Dashboard.js
import React from "react";
import { useNavigate } from "react-router-dom";

export default function Dashboard() {
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem("token");
    navigate("/");
  };

  return (
    <div style={{ padding: "20px" }}>
      <h1>Ecommerce Dashboard</h1>
      <p>Welcome! You are now logged in.</p>

      <ul>
        <li>
          <a href="/analytics">Go to Analytics</a>
        </li>
        <li>
          <button onClick={handleLogout}>Logout</button>
        </li>
      </ul>
    </div>
  );
}
