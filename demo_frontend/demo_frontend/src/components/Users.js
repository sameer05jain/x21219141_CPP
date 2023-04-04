import '../App.css';
import './Users.css';
import UserForm from './UserForm';
import logo from '../logo.svg';
import { useParams } from 'react-router-dom';

function Users() {
    let id = useParams();
    
    return (
        <div className='Users'>
            <nav className="App-header">
                <img src={logo} alt="logo" className="App-logo"></img>
                <h1 className="App-name">Demo Project</h1>
                <a href="/" className="users"><button type="button" className="btn btn-info">Home</button></a>
                <a href="/accounts" className="account"><button type="button" className="btn btn-warning">Add Account</button></a>
            </nav>
            <div className='Users-content'>
                <UserForm id={id} />
            </div>
        </div>
    );
}

export default Users;