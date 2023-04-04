import './UserInfo.css';

function UserInfo(props){
    const updateAccount = async (event,id) => {
        try {
            let data = {}
            if(document.getElementById("bn" + id).value !== ""){
                data.branchName = document.getElementById("bn" + id).value
            }
            if(document.getElementById("ab" + id).value !== ""){
                data.accountBalance = document.getElementById("ab" + id).value
            }
            if(document.getElementById("type" + id).value !== ""){
                data.accountType = document.getElementById("type" + id).value
            }
            let response = await fetch("http://localhost:8080/accounts/" + id, {
                method: "PUT",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data),
            });
            await response.json();
            if(response.ok){
                window.location.reload();
            }
        } catch (error) {
            console.log(error)
        }
    }

    const deleteAccount = async (event,id) => {
        try {
            let response = await fetch("http://localhost:8080/accounts/" + id, {
              method: "DELETE",
            });
            
            if(response.ok){
                window.location.reload();
            }
          } catch (error) {
            console.log(error);
          }
    }

    let accountsData = 
            props.userInfo.accounts.map(js => (
                <div className='accountUp'>
                <form className="user-account">
                    <input type="branchName" className="form-control inpa" placeholder={js.branchName} id={"bn" + js.accountId}  name="branchName"></input>
                    <input type="text" className="form-control inpa" placeholder={js.accountBalance} id={"ab" + js.accountId} name="accountBalance"></input>
                    <select className="form-control inpa" id={"type" + js.accountId}>
                        <option value="" selected disabled hidden>Select type</option>
                        <option value="S">Savings</option>
                        <option value="C">Current</option>
                    </select>
                    <div className="btn btn-primary inpa" onClick={() => {updateAccount(this,js.accountId)}}>Update</div>
                    <div className="btn btn-danger inpa" onClick={() => {deleteAccount(this,js.accountId)}}>Delete</div>
                </form><br/>
                </div>
            ))

    return (
        <div className="modal">
            <div className='modal-content'>
                <div className='modal-header'>
                    <h4 className='modal-title'>User Details</h4>
                    <div className="btn btn-warning" onClick={props.onClose}>Close</div>
                </div>
                <div className='modal-body'>
                    <div className='left'>
                        <h2>Name</h2>
                        <h2>Email</h2>
                        <h2>Mobile Number</h2>
                        <h2>Secondary Number</h2>
                        <h2>Gender</h2>
                    </div>
                    <div className='right'>
                        <h2>{props.userInfo.name}</h2>
                        <h2>{props.userInfo.email}</h2>
                        <h2>{props.userInfo.mobileNumber}</h2>
                        <h2>{props.userInfo.secondaryNumber}</h2>
                        <h2>{props.userInfo.gender === "M" ? "Male" : "Female"}</h2>
                    </div>
                </div>
                {accountsData}
                <div className='modal-footer'>  
                </div>
            </div>
        </div>
    )
}

export default UserInfo;