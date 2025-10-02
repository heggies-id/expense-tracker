import React, { useState, useEffect } from 'react';
import apiClient from '../api/apiClient';
import ExpenseForm from '../components/ExpenseForm';
import ExpenseList from '../components/ExpenseList';
import Navbar from '../components/Navbar';
import '../components/Layout.css';

const ExpensesPage = () => {
  const [expenses, setExpenses] = useState([]);
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchExpenses();
  }, []);

  const fetchExpenses = async () => {
    try {
      setLoading(true);
      const response = await apiClient.get('/expenses');
      setExpenses(response.data.data);
      setError('');
    } catch (err) {
      setError('Failed to fetch expenses.');
      console.error(err);
    } finally {
        setLoading(false);
    }
  };

  const handleAddExpense = async (expenseData) => {
    try {
        await apiClient.post('/expenses', expenseData);
        fetchExpenses();
    } catch (err) {
        setError('Failed to add expense.');
        console.error(err);
    }
  };

  return (
    <>
      <Navbar />
      <div className="page-container">
        <h2>My Expenses</h2>
        {error && <p className="error-message">{error}</p>}
        <ExpenseForm onAddExpense={handleAddExpense} />
        {loading ? <p>Loading expenses...</p> : <ExpenseList expenses={expenses} />}
      </div>
    </>
  );
};

export default ExpensesPage;