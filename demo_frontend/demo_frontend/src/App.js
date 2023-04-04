import logo from './logo.svg';
import './App.css';
import { useState,useEffect } from 'react';
import card from './img_avatar.png';
import card2 from './img_avatar2.png';
import UserInfo from './components/UserInfo';

// 50100288193872
function App() {
  const [jsx, setJsx] = useState([]);
  const [show, setShow] = useState(false);
  const [modalIndex, setModalIndex] = useState();
  const getData = async (event) => {
      try {
        let response = await fetch("http://localhost:8080/users")
        let jsonResponse = await response.json();
        setJsx(jsonResponse);
      } catch (error) {
          console.log(error)
      }
  }
  useEffect(() => {
    getData()
  }, [])

  const deleteUser = async (event,id) => {
    try {
      let response = await fetch("http://localhost:8080/users/" + id, {
        method: "DELETE",
      });
      if(response.ok){
        getData();
      }
    } catch (error) {
      console.log(error);
    }
  }

  let users = null;

  if(jsx.length !== 0){
    users = jsx.map((js,index) => (
                <>
                {
                  show && modalIndex === index && 
                  <UserInfo onClose={() => {setShow(false)}} show={show} userInfo={js}  />
                }
                <div className="card" onClick={() => {
                  setModalIndex(index)
                  setShow(true)}} >
                  <img src={js.gender==="M" ? card : card2}  alt="Avatar" className='cardImg' />
                  <div className="Cardcontainer">
                    <h4><b>{js.name}</b></h4>
                    <p>{js.email}</p>
                    <p>Accounts - {js.accounts.length}</p>
                    <a href={'/users/' + js.userId} onClick={(e)=>e.stopPropagation()} className="users"><button type="button" className="btn btn-success cvd">Update</button></a>
                    <button onClick={(e) => {
                      e.stopPropagation();
                      deleteUser(this,js.userId);
                      }} type="button" className="btn btn-danger cvd Vd">Delete</button>
                  </div>
                </div>
                </>
            ));
  }else {
    users = <h1 className='No-users'>No users present!</h1>
  }

  return (
    <div className="App">
        <nav className="App-header">
          <img src={logo} alt="logo" className="App-logo"></img>
          <h1 className="App-name">Demo Project</h1>
          <a href="/users" className="users"><button type="button" className="btn btn-info">Add User</button></a>
          <a href="/accounts" className="account"><button type="button" className="btn btn-warning">Add Account</button></a>
        </nav>
        <div className='App-body'>
          {users}
        </div>
    </div>
  );
}

export default App;
