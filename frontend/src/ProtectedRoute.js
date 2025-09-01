// src/ProtectedRoute.js
import React from "react";
import { Navigate } from "react-router-dom";

export default function ProtectedRoute({ children }) {
  const token = localStorage.getItem("token");

  if (!token) {
    // No token → send back to login
    return <Navigate to="/" replace />;
  }

  return children;
}
