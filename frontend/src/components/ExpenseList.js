import React from 'react';
import './Layout.css';

const formatter = new Intl.NumberFormat('id-ID', {
  style: 'currency',
  currency: 'IDR',
  minimumFractionDigits: 0,
  maximumFractionDigits: 2
});

const ExpenseList = ({ expenses }) => {
  if (expenses.length === 0) {
    return <p>No expenses found. Add one above!</p>;
  }

  return (
    <div className="expense-list">
      <h3>Your Expenses</h3>
      <ul>
        {expenses.map((expense) => (
          <li key={expense._id}>
            <span>{expense.description}</span>
            <span className="expense-amount">{formatter.format(expense.amount)}</span>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ExpenseList;