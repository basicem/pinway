import React, { useEffect, useState } from "react";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";
import Loader from "components/Loader";

import { addPost } from 'api/posts';
import { getCollectionsForUser, addPostToCollection } from 'api/collections';

const params = {
  id: 2
}

const PostCreate = () => {

    const [files, setFiles] = useState(null);
    const [fileURL,setFileURL] = useState("");
    //state for checking file size
    const [fileSize, setFileSize] = useState(true);
    // for file upload progress message
    const [fileUploadProgress, setFileUploadProgress] = useState(false);
    //for displaying response message
    const [fileUploadResponse, setFileUploadResponse] = useState(null);

    const [title, setTitle] = useState('')
    const [description, setDescription] = useState('')
    const [hashtags, setHashtags] = useState('')
    const [postString, setPostString] = useState({})
    const [userCollections, setUserCollections] = useState()
    const [collection, setCollection] = useState(null)
    const [selectedCollectionName, setSelectedCollectionName] = useState('')
    const [loading, setLoading] = useState(true);

    const [fileError, setFileError] = useState('');
    const [descriptionError, setDescriptionError] = useState('');
    const [titleError, setTitleError] = useState('');
    const [collectionError, setCollectionError] = useState('');
    //base end point url

    const navigate = useNavigate();

    const handleFileUpload = async (e) => {
        let error = false;
        e.preventDefault()
        setTitleError('');
        setDescriptionError('');
        setCollectionError('');
        setFileError('')
        if(files == null)
        {
          setFileError("Please add an image you wish to upload");
          error = true;
        }
        if(title.length == 0){
          setTitleError("Please add a title");
          error = true;
        }
        if(title.length > 50){
          setTitleError("Title must contain less than 50 characters");
          error = true;
        }
        if(description.length > 500){
          setDescriptionError("Description must contain less than 500 characters");
          error = true;
        }
        if(selectedCollectionName == ''){
          setCollectionError("Please pick a collection");
          error = true;
        }

        if(error){
          return;
        }

        try {
          const response = await addPost(
            files,
            {
                title : title,
                description : description,
                image_path : '',
                hashtagNames: hashtags.split(/\s+/).filter(substring => substring !== ""),
                id: 1,
                user_id: localStorage.getItem("UserId")
                })
          const response2 = await addPostToCollection(collection, {"postId": response.id, actionUserId: localStorage.getItem("UserId")} )
          toast.success("Post created!");
          navigate("/users/profile");
        } catch (err) {
          toast.error("Post creation failed!" + err);
          console.log(postString)
        }
      };

    const uploadFileHandler = (e) => {
        setFiles(e.target.files);
        setFileURL(URL.createObjectURL(e.target.files[0]));
       };

    useEffect(() => {
      setTitle('');
      setDescription('');
      setHashtags(['']);
      setSelectedCollectionName('')
      setTitleError('');
      setDescriptionError('');
      setCollectionError('');
      setFileError('')
      setHashtags('');

      const fetch = async () => {
        try {
          const response = await getCollectionsForUser(localStorage.getItem("UserId"));
          setUserCollections(response);
          if(userCollections.length > 0){
            setCollection(userCollections[0].id)
            setSelectedCollectionName(userCollections[0].name)
          }
        }catch (e) {
          setHashtags('');
          //setError("Unable to fetch collections for user!");
        } finally {
          setLoading(false);
        }
      };
      fetch();
        
    }, [params.id]);

    const handleTitleChange = (e) => setTitle(e.target.value);
    const handleDescriptionChange = (e) => setDescription(e.target.value);
    const handleHashtagChange = (e) => { setHashtags(e.target.value);}
    const handleCollectionItemClick = (item) => {
      setCollection(item.id)
      setSelectedCollectionName(item.name)
    }

    return (
      <div>
        <Loader isLoading={loading} />
        {userCollections && (
        <div className="container rounded row mx-auto my-5 px-2 py-3"  style={{background: '#d7a8f5 100%', width: '50%'}}>
            <div>
              <div className="row">
                <div className="col-6">
                  <div className="mt-2 container ">
                    <form className="rounded pt-5 d-flex align-items-center flex-column" style={{background: 'white', height: '600px'}}>
                          <figure className="text-center" style={{color: 'grey'}}>
                            <blockquote className="blockquote">
                              <p>Add the image you</p>
                              <p>wish to post</p>
                            </blockquote>
                          </figure>
                          {fileURL &&(<img  className = "rounded img-fluid max-width: 100% height: auto my-auto overflow-hidden mb-2"
                          src={fileURL}></img>)}
                        <div className="container rounded mt-auto mb-2 w-75" >
                          <input className="form-control" type="file" onChange={uploadFileHandler} id="formFile"  style={{background: '#d7a8f5 100%', color: 'grey'}}/>
                        </div>
                        {fileError && <p style={{ color: 'red', fontSize: '12px', marginTop: '5px' }}>{fileError}</p>}
                        {!fileSize && <p style={{color:'red'}}>File size exceeded!!</p>}
                        {fileUploadProgress && <p style={{color:'red'}}>Uploading File(s)</p>}
                        {fileUploadResponse!=null && <p style={{color:'green'}}>{fileUploadResponse}</p>}
                    </form>
                  </div>
                </div>
                <div className="col-6 rounded pt-4 d-flex align-items-center flex-column">
                  <div className="row w-100">
                    <div className='form-group w-100'>
                      <label className='form-label rounded' style={{fontSize:20}} >Title</label>
                      <input
                          className='form-control'
                          type='text'
                          name='title'
                          value = {title}
                          placeholder='Give your pin a title'
                          onChange = {handleTitleChange}
                      />
                       {titleError && <p style={{ color: 'red', fontSize: '12px', marginTop: '5px' }}>{titleError}</p>}
                    </div>
                  </div>
                  <div className="row w-100">
                    <div className="form-group">
                        <label htmlFor="textDesc" style={{color: "white", fontSize:20}}>Description</label>
                        <textarea className="form-control" id="textDesc"  onChange = {handleDescriptionChange} rows="3" placeholder="Say more about this pin"></textarea>
                        {descriptionError && <p style={{ color: 'red', fontSize: '12px', marginTop: '5px' }}>{descriptionError}</p>}
                      </div>
                    </div>
                  <div className="row w-100 mt-4">
                    <div className='form-group col-9'>
                        <input
                            className='form-control'
                            type='text'
                            name='collection'
                            value = {selectedCollectionName}
                            placeholder=''
                            disabled
                        />
                    </div>
                    <div className="dropdown col-3">
                      <button className="btn btn-light text-secondary mb-1 btn dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                        Share
                      </button>
                      <div className="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton">
                        {userCollections.map((collection) => (
                          <button
                            key={collection.collectionDTO.id}
                            className="dropdown-item"
                            type="button"
                            onClick={() => handleCollectionItemClick(collection.collectionDTO)}
                          >
                            {`${collection.collectionDTO.name}`}
                          </button>
                        ))}
                      </div>
                    </div>
                    {collectionError && <p style={{ color: 'red', fontSize: '12px', marginTop: '5px' }}>{collectionError}</p>}
                  </div>

                  <div className='form-inputs rounded mt-auto '>
                      <div className="form-group">
                        <label htmlFor="exampleFormControlTextarea1" style={{color: "white", fontSize:20}}>Hashtags</label>
                        <textarea className="form-control" id="exampleFormControlTextarea1"  onChange = {handleHashtagChange} rows="3"  placeholder="Add tags that describe your pin"></textarea>
                      </div>
                  </div>
                </div>
              </div>
              <div className="row">
                <div className="offset-10 col-2 mt-2">
                  <button className="btn btn-light btn-blk w-100" onClick={handleFileUpload}>Upload</button>
                </div>
              </div>
            </div>
        </div>
        )}
      </div>
    )
};

export default PostCreate;