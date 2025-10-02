import React, { useState } from 'react';
import './Layout.css';

const CATEGORIES = [
  "Food", 
  "Transportation", 
  "Utilities", 
  "Entertainment", 
  "Health", 
  "Other"
];

const ExpenseForm = ({ onAddExpense }) => {
  const [description, setDescription] = useState('');
  const [amount, setAmount] = useState('');
  const [category, setCategory] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!description || !amount || !category) return;
    
    onAddExpense({
      date: new Date().toISOString().slice(0, 10),
      description,
      amount: parseFloat(amount),
      category,
    });
    
    setDescription('');
    setAmount('');
    setCategory('');
  };

  return (
    <form onSubmit={handleSubmit} className="expense-form">
      <h3>Add New Expense</h3>
      <div className="form-group">
        <label htmlFor="description">Description</label>
        <input
          type="text"
          id="description"
          value={description}
          onChange={(e) => setDescription(e.target.value)}
          placeholder="e.g., Coffee"
          required
        />
      </div>
      <div className="form-group">
        <label htmlFor="category">Category</label>
        <select
          id="category"
          value={category}
          onChange={(e) => setCategory(e.target.value)}
          required
        >
          <option value="" disabled>-- Select a category --</option>
          
          {CATEGORIES.map(cat => (
            <option key={cat} value={cat}>
              {cat}
            </option>
          ))}
        </select>
      </div>

      <div className="form-group">
        <label htmlFor="amount">Amount</label>
        <input
          type="number"
          id="amount"
          value={amount}
          onChange={(e) => setAmount(e.target.value)}
          placeholder="e.g., 15000"
          required
          step="100"
        />
      </div>
      <button type="submit" className="btn-primary">Add Expense</button>
    </form>
  );
};

export default ExpenseForm;