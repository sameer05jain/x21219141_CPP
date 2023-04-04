import '../App.css';
import './Accounts.css';
import logo from '../logo.svg';
import AccountForm from './AccountForm'

function Accounts(){
    return (
        <div className='Accounts'>
            <nav className="App-header">
                <img src={logo} alt="logo" className="App-logo"></img>
                <h1 className="App-name">Demo Project</h1>
                <a href="/" className="users"><button type="button" className="btn btn-info">Home</button></a>
                <a href="/users" className="account"><button type="button" className="btn btn-warning">Add User</button></a>
            </nav>
            <div className='Accounts-content'>
                <AccountForm />
            </div>
        </div>
    );
}

export default Accounts;