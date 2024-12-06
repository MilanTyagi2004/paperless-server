import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import LoginForm from "./Components//Auth/LoginForm.js";
import Dashboard from "./Components/Dashboard.js"; // Import your dashboard page

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<LoginForm />} />
        <Route path="/dashboard" element={<Dashboard />} />
        {/* Add other routes here as necessary */}
      </Routes>
    </Router>
  );
};

export default App;
