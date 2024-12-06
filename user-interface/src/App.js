import React from "react";
import Sidebar from "./Components/Slide.js";
import Header from "./Components/Header.js";
import Card from "./Components/Card.js";
import Transactions from "./Components/Transactions.js";
import Footer from "./Components/Footer.js";
import "./App.css"; // For global styling if necessary
import LoginForm from "./Components/Auth/LoginForm.js"

function App() {
  return (
    <div className="app">
      <LoginForm/>
      {/* <Sidebar /> */}
      {/* <div className="main-content">
        <Header />
        <div className="dashboard">
          <div className="card-section">
            <Card title="Total Sales" value="$23,577.22" subtitle="Per Day" />
            <Card title="Total Visitors" value="57,711" subtitle="Per Day" />
          </div>
          <Transactions />
        </div>
        <Footer />
      </div> */}
    </div>
  );
}

export default App;
