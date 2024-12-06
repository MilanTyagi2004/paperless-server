import React, { useState } from "react";
import styles from "./Auth.module.css";

const SignUp = () => {
  const [formData, setFormData] = useState({
    name: "",
    email: "",
    password: "",
    confirmPassword: "",
  });

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSignup = (e) => {
    e.preventDefault();
    if (formData.password !== formData.confirmPassword) {
      alert("Passwords do not match!");
      return;
    }
    console.log("Signup Details:", formData);
  };

  return (
    <form className={styles.form} onSubmit={handleSignup}>
      <h2>Create Your Account</h2>
      <div className={styles.inputGroup}>
        <label>Full Name</label>
        <input
          type="text"
          placeholder="Enter your full name"
          name="name"
          value={formData.name}
          onChange={handleInputChange}
          required
        />
      </div>
      <div className={styles.inputGroup}>
        <label>Email</label>
        <input
          type="email"
          placeholder="Enter your email"
          name="email"
          value={formData.email}
          onChange={handleInputChange}
          required
        />
      </div>
      <div className={styles.inputGroup}>
        <label>Password</label>
        <input
          type="password"
          placeholder="Create a password"
          name="password"
          value={formData.password}
          onChange={handleInputChange}
          required
        />
      </div>
      <div className={styles.inputGroup}>
        <label>Confirm Password</label>
        <input
          type="password"
          placeholder="Confirm your password"
          name="confirmPassword"
          value={formData.confirmPassword}
          onChange={handleInputChange}
          required
        />
      </div>
      <button className={styles.submitButton} type="submit">
        Sign Up
      </button>
    </form>
  );
};

export default SignUp;
