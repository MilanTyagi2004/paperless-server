import React from "react";
import "../Style/Transactions.css";

function Transactions() {
  const transactions = [
    { id: 1, name: "Apple Watch", date: "15 May 2023", amount: "$399", status: "Completed" },
    { id: 2, name: "Samsung Galaxy", date: "14 May 2023", amount: "$699", status: "Pending" },
    { id: 3, name: "MacBook Pro", date: "13 May 2023", amount: "$1,299", status: "Failed" },
    { id: 4, name: "Dell Monitor", date: "12 May 2023", amount: "$249", status: "Completed" },
  ];

  return (
    <div className="transactions">
      <h2>Latest Transactions</h2>
      <table>
        <thead>
          <tr>
            <th>Item</th>
            <th>Date</th>
            <th>Amount</th>
            <th>Status</th>
          </tr>
        </thead>
        <tbody>
          {transactions.map((transaction) => (
            <tr key={transaction.id}>
              <td>{transaction.name}</td>
              <td>{transaction.date}</td>
              <td>{transaction.amount}</td>
              <td className={`status ${transaction.status.toLowerCase()}`}>
                {transaction.status}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default Transactions;
