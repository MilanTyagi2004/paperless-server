// Dashboard.js
import React from "react";
import Sidebar from "./Slide.js";
import Header from "./Header.js";
import Card from "./Card.js";
import Transactions from "./Transactions.js";
import Footer from "./Footer.js";

const Dashboard = () => {
  return (
    <div>
      <Sidebar />
      <div className="main-content">
        <Header />
        <div className="dashboard">
          <div className="card-section">
            <Card title="Total Sales" value="$23,577.22" subtitle="Per Day" />
            <Card title="Total Visitors" value="57,711" subtitle="Per Day" />
          </div>
          <Transactions />
        </div>
        <Footer />
      </div>
    </div>
  );
};

export default Dashboard;
