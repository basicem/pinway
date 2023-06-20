import React from 'react';
import { useEffect, useState } from "react";
import { toast } from "react-toastify";
import { useNavigate, useLocation } from "react-router-dom";
import './Scrollbar.css';
import Logo from 'images/pinway_logo.png';
import placeholder from  "images/place_holder.png";

import { getPostById , getCommentsForPost, addComment, addLike} from 'api/posts';
import { getUserById, getFollowersForUser, addFollowerForUser } from 'api/users';
import { getCollectionsForUser, addPostToCollection } from 'api/collections';
import Loader from "components/Loader";


const PostDetails = () => {

    const [postDetails, setPostDetails] = useState();
    const [UserDetails, setUserDetails] = useState();
    const [postComments, setPostComments] = useState();
    const [loggedInUserDetails, setLoggedInUserDetails] = useState();
    const [newCommentContent, setNewCommentContent] = useState("");
    const [userCollections, setUserCollections] = useState()
    const [collection, setCollection] = useState(null)
    const [selectedCollectionName, setSelectedCollectionName] = useState()
    const [loading, setLoading] = useState(true);
    const [followers, setFollowers] = useState();
    const [isVisible, setIsVisible] = useState(null);

    const location = useLocation();
    const postId = location.state.id;

    const navigate = useNavigate();

    const handleOpenUserDetails = async (item) => {
      const id = item.userDTO.id;

      if (id === parseInt(localStorage.getItem("UserId")))
        navigate("/users/profile");
      else 
        navigate("/users/details", { state: { id } });
    };

    const handleOpenCreator = async () => {
      const id = UserDetails.id;

      if (id === parseInt(localStorage.getItem("UserId")))
        navigate("/users/profile");
      else 
        navigate("/users/details", { state: { id } });
    };

    const handleOpenLogged = async () => {
        navigate("/users/profile");
    };



    const handleCommentChange = async(e) => {
      setNewCommentContent(e.target.value)
    }

    const addCommentHandle = async () => {
        if (newCommentContent.length === 0){
          return;
        }
        const data = {id:0, content:newCommentContent, post_id: postId, user_id: loggedInUserDetails.id}
        const response = await addComment(data)

        const comments = await getCommentsForPost(postDetails.postDTO.id)
        setPostComments(comments)

        setNewCommentContent('');

      };
    
    const addLikeHandle = async(data) => {
      await addLike(data);
      const comments = await getCommentsForPost(postDetails.postDTO.id)
      setPostComments(comments)

    }

    const handleFollow = async(user) => {
      console.log("You want to folow user ", user.name);
      await addFollowerForUser(loggedInUserDetails.id, {id: UserDetails.id})
    };

    const handleCollectionItemClick = (item) => {
      setCollection(item.id)
      setSelectedCollectionName(item.name)
    }
    
    const handleAddToCollection = async(e) => {
      if(collection == null){
        toast.error("Please pick a collection");
        return;
      }

      if(userCollections.length == 0){
        toast.error("No collections available");
        return;
      }
      if( selectedCollectionName.length == 0){
        toast.error("Please select the connection you wish to pin the post to!");
        return;
      }
      try {
        await addPostToCollection(collection, {"postId": postDetails.postDTO.id, actionUserId: localStorage.getItem("UserId")} )
        toast.success("Post added to collection!");
      } catch (err) {
        toast.error("Failed to add the post to collection!");
      }
    }
    useEffect(() => {
      const fetch = async () => {
        try{
          const response = await getPostById(postId)
          setPostDetails(response)
          const user = await getUserById(response.userDTO.id)
          setUserDetails(user)

          const comments = await getCommentsForPost(response.postDTO.id)
          setPostComments(comments)

          const loggedInUser = await getUserById(localStorage.getItem("UserId"))
          setLoggedInUserDetails(loggedInUser)

          const collectResponse = await getCollectionsForUser(localStorage.getItem("UserId"));
          setUserCollections(collectResponse);

          if(userCollections.length > 0){
            setCollection(collectResponse[0].id)
            setSelectedCollectionName(collectResponse[0].name)
          }

        }catch (e) {
        } finally {
          setLoading(false);
        }
        
      }
      fetch();
    }, []);
  

    return (
      <div>
        <Loader isLoading={loading} />
        {postDetails && UserDetails && postComments && loggedInUserDetails && userCollections &&  (
        <div className="container rounded row mx-auto my-5 px-2 py-3 w-75"  style={{background: '#d7a8f5 100%'}}>
          <div>
            <div className="row">
              <div className="col-6 rounded pt-4 d-flex align-items-center flex-column">
                <div className="mt-2 container rounded d-flex align-items-center flex-column">
                  <div className="row w-100">
                    <div className="rounded" style={{height: '400px'}}>
                      <div className="w-100 h-100  d-flex align-items-center flex-column align-middle rounded" style={{background:'white'}}>
                        <img key={postDetails.postDTO.id} alt={placeholder}
                          className = "rounded img-fluid max-width: 100% height: auto my-auto overflow-hidden"
                          src={"http://localhost:8080/post-photos/" + postDetails.postDTO.id + "/" + postDetails.postDTO.image_path}></img>
                      </div>
                    </div>
                  </div>
                  

                  <div className="row w-100">
                    <div className="col-3 mt-2">
                      <div className="dropdown col-3">
                        <button className="btn btn-light text-secondary mb-1 btn dropdown-toggle btn btn-blk" type="button" id="MenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                          Collection
                        </button>
                        <div className="dropdown-menu dropdown-menu-start" aria-labelledby="dropdownMenuButton">
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
                    </div>
                    <div className="col-6 mt-2">
                        <div className='form-group '>
                            <input
                                className='form-control'
                                type='text'
                                name='collection'
                                value = {selectedCollectionName}
                                placeholder='Not selected'
                                disabled
                            />
                        </div>
                      </div>
                    <div className="col-3 mt-2">
                      <button className="btn btn-light btn-blk w-100" onClick={handleAddToCollection}>Pin</button>
                    </div>
                  </div>

                  <div className="row w-100">
                    <div className="form-group">
                        <label htmlFor="textDesc" style={{color: "white", fontSize:20}}>{postDetails.postDTO.title}</label>
                        <textarea className="form-control" id="textDesc"  disabled rows="3" value={postDetails.postDTO.description}></textarea>
                    </div>
                  </div>
                  
                {/* user details of the post left side and a FOLLOW BUTTON */}
                  <div className="row w-100 mt-2">
                    <div onClick={handleOpenCreator}  style={{cursor: 'pointer'}} className="col-8 d-flex align-items-center">
                      <div className="col-2 me-3">
                        <img 
                          src={"http://localhost:8083/user-photos/" + UserDetails.id + "/" + UserDetails.image_path}
                          alt={placeholder} 
                          className="rounded-circle" style={{height:"50px", width:"50px"}}></img>
                      </div>
                      <div className="col-10 ">
                        <p className="text-center-left mb-0"  style={{ fontSize: 16, color: "white" }}>{UserDetails.name + " "+ UserDetails.surname}</p>
                        <p className="text-center-left mb-0" style={{ fontSize: 10, color: "white" }}>{UserDetails.following.length} Following</p>
                      </div>
                    </div>
                    {UserDetails.id != loggedInUserDetails.id && (<div className="offset-1 col-3 d-flex align-items-center">
                      <button 
                        className="btn btn-light btn-block w-100"
                        // style={{ display: isVisible ? 'block' : 'none' }}
                        style={{ display: isVisible ? 'block' : 'none' }}
                        onClick={() => {handleFollow(UserDetails)}}
                      >Follow</button>
                    </div>)}
                  </div>
                  

                </div>
                </div>
                  <div className="col-6 p-0 rounded d-flex align-items-center flex-column">
                    {/* first part right */}
                    <div className="mt-4 w-100 container h-100 ">
                      <div className="row w-100 h-100">
                        <div className="rounded  w-100 h-100">
                          <div className="w-100 h-100  d-flex align-items-center flex-column align-middle rounded" style={{borderStyle:'solid', borderColor:'white', borderWidth:'thin'}}>
                              <p className="text-end" style={{color:"white", fontSize:22}}>Comments</p>
                              <div className="container custom-scrollbar" style={{height:"350px"}}>
                                {postComments.map((comment)=>(
                                  <div key={comment.id} className="card mx-auto mt-2" style={{background:"white", width:"90%"}}>
                                  <div className="card-header p-1">
                                    <div className="row">
                                      <div className="col-9">
                                        <p className="text-center-left mb-0"  
                                          style={{ fontSize: 16, cursor: 'pointer'}}
                                          onClick={() => {handleOpenUserDetails(comment)}} 
                                        >{comment.userDTO.name + " " + comment.userDTO.surname}</p>
                                      </div>
                                      { !comment.commentDTO.likes.some(like => {
                                        return like.userId == localStorage.getItem("UserId")  && like.commentId === comment.commentDTO.id
                                      }) && (<div className="col-3 text-end">
                                        <button className="btn" onClick={() => addLikeHandle({commentId: comment.commentDTO.id, userId: localStorage.getItem("UserId")})}>
                                          <svg className="http://www.w3.org/2000/svg bi bi-heart" width="16" height="16" fill="currentColor" viewBox="0 0 16 16">
                                            <path d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01L8 2.748zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15z"/>
                                          </svg>
                                        </button>
                                      </div>)}

                                      { comment.commentDTO.likes.some(like => {
                                        return like.userId == localStorage.getItem("UserId") && like.commentId === comment.commentDTO.id
                                      }) && (<div className="col-3 text-end">
                                        <button className="btn" onClick={() => addLikeHandle({commentId: comment.commentDTO.id, userId: localStorage.getItem("UserId")})}>
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="red" className="bi bi-heart-fill" viewBox="0 0 16 16">
                                          <path fillRule="evenodd" d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z"/>
                                        </svg>
                                        </button>
                                      </div>)}
                                    </div>
                                  </div>
                                  <div className="card-body p-2">
                                    <p className="text-center-left mb-0" style={{ fontSize: 14}}>{comment.commentDTO.content}</p>
                                  </div>
                                </div>
                                ))}
                              </div>
                              <div className="d-flex align-items-center flex-column" style={{width:"90%"}}>
                                <div className="row w-100 mt-5">
                                  <div className="form-group">
                                      <label htmlFor="textDesc"  style={{color: "white", fontSize:20}}>Add a comment</label>
                                      <textarea className="form-control" id="textDesc" placeholder='Add a comment...' rows="3" value={newCommentContent} onChange={handleCommentChange}></textarea>
                                  </div>
                                </div>
                                <div className="row w-100 mt-2 w-100">
                                  <div  onClick={handleOpenLogged} style={{cursor:'pointer'}} className="col-8 d-flex align-items-center ">
                                    <div className="col-2 me-3">
                                      <img src={"http://localhost:8083/user-photos/" + loggedInUserDetails.id + "/" + loggedInUserDetails.image_path } alt="" className="rounded-circle" style={{height:"50px", width:"50px"}}></img>
                                    </div>
                                    <div className="col-10 ">
                                      <p className="text-center-left mb-0"  style={{ fontSize: 16, color: "white" }}>{loggedInUserDetails.name + " " + loggedInUserDetails.surname}</p>
                                      <p className="text-center-left mb-0" style={{ fontSize: 10, color: "white" }}>{loggedInUserDetails.following.length} Following</p>
                                    </div>
                                  </div>
                                  <div className="col-4 d-flex align-items-center">
                                    <button className="btn btn-light btn-block w-100" onClick={addCommentHandle}>Comment</button>
                                  </div>
                                </div>  
                              </div>      
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
            </div>
      </div>)}
    </div>
)
}

export default PostDetails;