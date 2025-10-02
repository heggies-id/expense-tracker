import useAuth from '../hooks/useAuth';
import './Layout.css';

const Navbar = () => {
    const { logout } = useAuth();

    return (
        <nav className="navbar">
            <div className="navbar-brand">Expense Tracker</div>
            <button onClick={logout} className="btn-secondary">Logout</button>
        </nav>
    );
};

export default Navbar;