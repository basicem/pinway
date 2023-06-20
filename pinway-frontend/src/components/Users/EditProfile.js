import React, { useEffect, useState } from "react";
import './EditProfile.css';
import { getUserById, updateUser, addUserPhoto} from "api/users";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";
import placeholder from  "images/place_holder.png";

const EditProfile = () => {

  const [files, setFiles] = useState(null);
  //state for checking file size
  const [fileSize, setFileSize] = useState(true);
  // for file upload progress message
  const [fileUploadProgress, setFileUploadProgress] = useState(false);
  //for displaying response message
  const [fileUploadResponse, setFileUploadResponse] = useState(null);

  const [username, setUsername] = useState('');
  const [name, setName] = useState('');
  const [surname, setSurname] = useState('');
  const [user, setUSer] = useState('');

  const [nameError, setNameError] = useState('');
  const [usernameError, setUsernameError] = useState('');
  const [surnameError, setSurnameError] = useState('');

  const navigate = useNavigate();

  const uploadFileHandler = (event) => {
    setFiles(event.target.files);
   };

  const handleUpdateUSer = () =>{
     const data = {
      name: name,
      surname: surname,
      username: username,
      email: user.email,
      userVisibilityType:{
          id:1,
          type: "PUBLIC"
      },
      following:[],
      followers:[],
      numOfFollowing:0,
      numOfFollowers:0,
      userCollections:[]
  }

  setUsernameError('');
  setNameError('');
  setSurnameError('');

  let error = false 
  if(username.length == 0){
    setUsernameError("Please set the username");
    error = true;
  }
  if(name.length == 0){
    setNameError("Please set the name");
    error = true;
  }
  if(surname.length == 0){
    setSurnameError("Please set the surname");
    error = true;
  }
  if (error){
    return;
  }
    try{
      updateUser(user.id, data);
      if(files != null){
        addUserPhoto(files,{
          "id":user.id
        })
      }
    } catch(e){
      toast.error("Some changes might hot have been saved")
    } finally{
      if(!error){
        navigate("/users/profile")
      }
    }
  }

   const handleUsernameChange = (e) => setUsername(e.target.value);
   const handleNameChange = (e) => setName(e.target.value);
   const handleSurnameChange = (e) => setSurname(e.target.value);

   useEffect(() => {
    const fetch = async () => {
      try {
        const response = await getUserById(localStorage.getItem("UserId"));
        setUSer(response);
        setName(response. name)
        setUsername(response.username)
        setSurname(response.surname)
      }catch (e) {
        //setError("Unable to fetch collections for user!");
      } finally {
        //setLoading(false);
      }
    };
    fetch();
      
  }, []);

  return (
   <div className="container w-75">
       <h5 className='mb-3 mt-3'>Edit Profile</h5>
       <p className='mb-5'>People visiting your profile will see the following info</p>
       <div className="col-10 mx-auto">
        <div className="row">
          <div className="col-4">
          {user.image_path ? ( 
          <img className="rounded-circle  img-responsive" src={"http://localhost:8083/user-photos/" + user.id + "/" + user.image_path} alt='currentPhotoURL' style={{borderRadius:"50%", width:"200px", height:"200px",objectFit:"cover"}} />) : (
            <img className="rounded-circle  img-responsive" src={placeholder} alt='currentPhotoURL' style={{borderRadius:"50%", width:"200px", height:"200px",objectFit:"cover"}} />
            )}
            
          </div>
          <div className="col-8">
            <div className="mt-2 container ">
              <form className="rounded pt-5 d-flex align-items-center flex-column" style={{background: 'white'}}>
                  <figure className="text-center" style={{color: 'grey'}}>
                    <blockquote className="blockquote">
                      <p>Change Photo</p>
                    </blockquote>
                  </figure>
                <div className="container rounded  w-75" >
                  <input className="form-control" type="file" onChange={uploadFileHandler} id="formFile"  style={{background: '#d7a8f5 100%', color: 'grey'}}/>
                </div>
                {!fileSize && <p style={{color:'red'}}>File size exceeded!!</p>}
                {fileUploadProgress && <p style={{color:'red'}}>Uploading File(s)</p>}
                {fileUploadResponse!=null && <p style={{color:'green'}}>{fileUploadResponse}</p>}
              </form>
            </div>
          </div>
       </div>

      </div>
       <div className='form-group'>
         <label htmlFor='username'>Username</label>
         <input
           className='form-control'
           type='text'
           id='username'
           name='username'
           placeholder= {user.username}
           value={username}
           onChange = {handleUsernameChange}
         />
         {usernameError && <p style={{ color: 'red', fontSize: '12px', marginTop: '5px' }}>{usernameError}</p>}
       </div>

       <div className='form-group'>
         <label htmlFor='name'>Name</label>
         <input
           className='form-control'
           type='text'
           id='name'
           name='name'
           placeholder= {user.name}
           value={name}
           onChange = {handleNameChange}
         />
         {nameError && <p style={{ color: 'red', fontSize: '12px', marginTop: '5px' }}>{nameError}</p>}
       </div>

       <div className='form-group'>
         <label htmlFor='email'>Surname</label>
         <input
           className='form-control'
           type='text'
           id='surname'
           name='surname'
           placeholder={user.surname}
           value={surname}
           onChange = {handleSurnameChange}
         />
         {surnameError && <p style={{ color: 'red', fontSize: '12px', marginTop: '5px' }}>{surnameError}</p>}
       </div>


      <div className="row">
        <div className="offset-10 col-2">
          <button className='btn btn-blk w-100 mt-2' style={{background: 'lightgrey 100%', color: 'grey'}} onClick={handleUpdateUSer}>Save</button>
        </div>
      </div>
   </div>

  );
};

export default EditProfile;
