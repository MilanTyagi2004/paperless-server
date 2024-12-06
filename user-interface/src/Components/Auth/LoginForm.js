import React, { useState } from "react";
import styles from "./Auth.module.css";

const LoginForm = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = (e) => {
    e.preventDefault();
    console.log("Login Details:", { email, password });
  };

  return (
    <form className={styles.form} onSubmit={handleLogin}>
      <h2>Welcome Back!</h2>
      <div className={styles.inputGroup}>
        <label>Email</label>
        <input
          type="email"
          placeholder="Enter your email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
        />
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
      </div>
      <button className={styles.submitButton} type="submit">
        Login
      </button>
    </form>
  );
};

export default LoginForm;
