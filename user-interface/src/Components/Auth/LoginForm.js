import React, { useState } from "react";
import { useNavigate } from "react-router-dom"; // Import useNavigate
import styles from "./Auth.module.css";

const LoginForm = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [errors, setErrors] = useState({});
  const navigate = useNavigate(); // Initialize useNavigate

  const validateForm = () => {
    const newErrors = {};
    const emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;

    if (!email) {
      newErrors.email = "Email is required";
    } else if (!emailRegex.test(email)) {
      newErrors.email = "Invalid email format";
    }

    if (!password) {
      newErrors.password = "Password is required";
    } else if (password.length < 6) {
      newErrors.password = "Password must be at least 6 characters long";
    }

    setErrors(newErrors);

    // Return true if no errors
    return Object.keys(newErrors).length === 0;
  };

  const handleLogin = (e) => {
    e.preventDefault();

    if (!validateForm()) return;

    // Simulate login success and navigate to dashboard
    alert("Logged in successfully!");
    console.log("Login Data:", { email, password });
    
    // Navigate to the dashboard page
    navigate("/dashboard");
  };

  return (
    <form className={styles.form} onSubmit={handleLogin}>
      <h2 className={styles.heading}>Welcome Back</h2>
      <p className={styles.subtext}>Please enter your details to log in.</p>
      <div className={styles.inputGroup}>
        <label>Email</label>
        <input
          type="email"
          placeholder="Enter your email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
        />
        {errors.email && <span className={styles.errorText}>{errors.email}</span>}
      </div>
      <div className={styles.inputGroup}>
        <label>Password</label>
        <input
          type="password"
          placeholder="Enter your password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
        />
        {errors.password && <span className={styles.errorText}>{errors.password}</span>}
      </div>
      <button className={styles.submitButton} type="submit">
        Log In
      </button>
      <p className={styles.switchText}>
        Don’t have an account?{" "}
        <span className={styles.switchLink}>Sign Up</span>
      </p>
    </form>
  );
};

export default LoginForm;
