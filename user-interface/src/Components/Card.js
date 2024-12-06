import React from "react";
import "../Style/Card.css";

function Card({ title, value, subtitle }) {
  return (
    <div className="card">
      <h2>{title}</h2>
      <p>{value}</p>
      <span>{subtitle}</span>
    </div>
  );
}

export default Card;

