import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";

import Loader from "components/Loader";
import { getCollectionsForUser, getUserById } from "api/users";
import { getPostsForUser } from "api/posts";
import placeholder from  "images/place_holder.png";
import monkey from  "images/monkey.png";
import collections_place_holder from "images/collections_place_holder.jpg";
import CollectionCreateModal from "components/Collections/CollectionCreateModal";
import { postCollection } from "api/collections";

const UserProfile = () => {

    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    const [collections, setCollections] = useState();
    const [posts, setPosts] = useState();
    const [user, setUser] = useState(null);
    const [isCollectionsModalOpen, setIsCollectionsModalOpen] = useState(false);
    const navigate = useNavigate();

    const handleEditProfile = () => {
      navigate('/users/profile/edit');
    }

    useEffect(() => {
        const fetch = async () => {
          try {
            setLoading(true);
            const responseUser = await getUserById(localStorage.getItem("UserId"));
            const response = await getCollectionsForUser(localStorage.getItem("UserId"));
            const responsePosts = await getPostsForUser(localStorage.getItem("UserId"));
            setUser(responseUser);
            setCollections(response.collectionDTOS);
            setPosts(responsePosts);
            setLoading(false);
          } catch (e) {
            setError("Unable to fetch User Details!");
          } finally {
            setLoading(false);
          }
        };
    
        fetch();
      }, [isCollectionsModalOpen]);

      const handleItemClick = async (item) => {
        // Handle item click event
        //console.log('Clicked ', item);
        //const id = item.id;
        navigate("/collections", { state: { item } });
      };

      const handlePostClick = async (item) => {
        // Handle item click event
        console.log(`Clicked ${item.id}`);
        const id = item.id;
        navigate("/posts/details", { state: { id } });
      };


      const handleOpenCollectionsModal = () => {
        setIsCollectionsModalOpen(!isCollectionsModalOpen);
      };

      const handleCreatePost = () => {
        navigate("/posts/create");
      }

      const handleCreateCollection = async (boardName, isPrivate) => {
        try {
          let type = "PRIVATE";
          let id = 1;
          if(isPrivate !== true) {
            type = "PUBLIC";
            id = 2;
          }
          const data = {
            "name": boardName,
            "collectionVisibilityType": {
              "id": id,
              "type": type
            },
            "created_at": "",
            "numOfPosts": 0,
            "userId": localStorage.getItem("UserId"),
            "deleted": false
          }

          console.log(data);
          
          await postCollection(data);
          toast.success("Collection added!");
        } catch (e) {
          toast.error("Unable to add collection!");
        } finally {
          setIsCollectionsModalOpen(!isCollectionsModalOpen);
        }
        
      }
    

    if (error) {
    return (
        <div className="container offset-2 col-8" style={{padding: '15px'}}>
        <div className="alert alert-danger" role="alert">
            {error}
        </div>
        </div>
    );}

    return (
      <div>
        {user && posts && collections && (
          <div>
            <CollectionCreateModal visible={isCollectionsModalOpen} handleClick={handleOpenCollectionsModal} handleCreateCollection={handleCreateCollection}></CollectionCreateModal>
            <Loader isLoading={loading} /> 
       
          <div className="offset-1 col-md-9 text-center mx-auto">
            {/* <img src={monkey} alt="" className="d-inline-block align-text-top" /> */}
            {user.image_path ? ( <img className="rounded-circle  img-responsive d-inline-block align-text-top" src={"http://localhost:8083/user-photos/" + user.id + "/" + user.image_path} alt={placeholder} style={{borderRadius:"50%", width:"150px", height:"150px",objectFit:"cover"}} />) : (
               <img className="rounded-circle  img-responsive d-inline-block align-text-top" src={placeholder} alt={placeholder} style={{borderRadius:"50%", width:"150px", height:"150px",objectFit:"cover"}} />
            )}
           
            <div style={{ margin: '0.5rem 0' }}>
              <div style={{ fontSize: '1.2rem' }}>{user.name} {user.surname}</div>
              <div className="text-secondary">@{user.username}</div>
              <div style={{ fontSize: '0.9rem' }}>
                {user.following.length} following
              </div>
            </div>
            <button 
              className="btn rounded" 
              style={{ backgroundColor: 'lightgrey', color: 'white' }}
              onClick={handleEditProfile}
            >Edit Profile</button>
          </div>

          <div className="offset-1 col-md-11 mx-auto mb-5 mt-3">
            <div className="card border-0">
              <div className="d-flex align-items-center justify-content-between">
                <div className="text-secondary m-2">Collections</div>
                <button className="btn btn-transparent" onClick={handleOpenCollectionsModal}>
                  <span style={{ color: 'grey', fontSize: '2.5rem' }}>+</span>
                </button>
              </div>
              <div className="row">
                <div>
                {collections.length === 0 ? (
                  <div className="card-body" style={{ border: '2px solid lightgrey', borderRadius: '10px', display: 'flex', gap: '5px', flexWrap: 'wrap' }}>
                    <div style={{ margin: 'auto',  width: '100%', height: '100%'}}>
                      <div className="m-3" style={{ color:'grey', textAlign: 'center' }}>There aren't any Collections yet</div>
                    </div>
                  </div>
                  ) : (
                  <div className="card-body" style={{ border: '2px solid lightgrey', borderRadius: '10px', display: 'flex', gap: '25px', flexWrap: 'wrap' }}>
                    {collections.map((collection) => (

                      <div onClick={() => handleItemClick(collection)} key={collection.collectionDTO.id} style={{ flexBasis: '18%', marginBottom: '0px', cursor: 'pointer' }}>
                          <div>
                            <div style={{ backgroundColor:'#F0F0F0', position: 'relative', paddingBottom: '50%', overflow: 'hidden', borderRadius: '8px' }}>
                              {collection.postDTO[0] && (
                                <img src={"http://localhost:8080/post-photos/" + collection.postDTO[0].id + "/" + collection.postDTO[0].image_path} alt={placeholder} style={{ position: 'absolute', top: '0', left: '0', width: '100%', height: '100%', objectFit: 'cover' }} />
                              )}
                            </div>

                            <div style={{ display: 'flex', justifyContent: 'space-between', marginTop: '3px' }}>
                              <div style={{ backgroundColor:'#F0F0F0', flexBasis: '49%', position: 'relative', paddingBottom: '40%', overflow: 'hidden', borderRadius: '8px' }}>
                                {collection.postDTO[1] && (
                                  <img src={"http://localhost:8080/post-photos/" + collection.postDTO[1].id + "/" + collection.postDTO[1].image_path} alt={placeholder} style={{ position: 'absolute', top: '0', left: '0', width: '100%', height: '100%', objectFit: 'cover' }} />
                                )}
                              </div>
                              <div style={{ backgroundColor:'#F0F0F0', flexBasis: '49%', position: 'relative', paddingBottom: '40%', overflow: 'hidden', borderRadius: '8px' }}>
                                {collection.postDTO[2] && (
                                  <img src={"http://localhost:8080/post-photos/" + collection.postDTO[2].id + "/" + collection.postDTO[2].image_path} alt={placeholder} style={{ position: 'absolute', top: '0', left: '0', width: '100%', height: '100%', objectFit: 'cover' }} />
                                )}
                              </div>
                            </div>
                          </div>

                      {/* <div>
                        <div style={{ position: 'relative', paddingBottom: '50%', overflow: 'hidden', borderRadius: '8px' }}>
                          {collection.postDTO[0] ? (
                            <img src={"http://localhost:8080/post-photos/" + collection.postDTO[0].id + "/" + collection.postDTO[0].image_path} alt={placeholder} style={{ position: 'absolute', top: '0', left: '0', width: '100%', height: '100%', objectFit: 'cover' }} />
                          ) : (
                            <img src={placeholder} alt="Placeholder" style={{ position: 'absolute', top: '0', left: '0', width: '100%', height: '100%', objectFit: 'cover' }} />
                          )}
                        </div>
                        <div style={{ display: 'flex', justifyContent: 'space-between', marginTop: '5px' }}>
                          {collection.postDTO[1] ? (
                            <div style={{ flexBasis: '48%', position: 'relative', paddingBottom: '40%', overflow: 'hidden', borderRadius: '8px' }}>
                              <img src={"http://localhost:8080/post-photos/" + collection.postDTO[1].id + "/" + collection.postDTO[1].image_path} alt={placeholder} style={{ position: 'absolute', top: '0', left: '0', width: '100%', height: '100%', objectFit: 'cover' }} />
                            </div>
                          ) : <img src={placeholder} alt="Placeholder" style={{ position: 'absolute', top: '0', left: '0', width: '100%', height: '100%', objectFit: 'cover' }} />}
                          {collection.postDTO[2] ? (
                            <div style={{ flexBasis: '48%', position: 'relative', paddingBottom: '40%', overflow: 'hidden', borderRadius: '8px' }}>
                              <img src={"http://localhost:8080/post-photos/" + collection.postDTO[2].id + "/" + collection.postDTO[2].image_path} alt={placeholder} style={{ position: 'absolute', top: '0', left: '0', width: '100%', height: '100%', objectFit: 'cover' }} />
                            </div>
                          ) : <img src={placeholder} alt="Placeholder" style={{ position: 'absolute', top: '0', left: '0', width: '100%', height: '100%', objectFit: 'cover' }} />}
                        </div>
                      </div> */}


                        <div>
                          <p className='m-0'>{collection.collectionDTO.name}</p>
                          <p className='text-secondary m-0' style={{ fontSize: '12px' }}>{collection.postDTO.length} Pins</p>
                        </div>

                      </div>          
                    
                    ))}
                  </div>
                  )}

                </div>
              </div>
            </div>
          </div>

          <div className="offset-1 col-md-11 mx-auto mb-5 mt-3">
            <div className="card border-0">
              <div className="d-flex align-items-center justify-content-between">
                <div className="text-secondary m-2">Posts</div>
                <button className="btn btn-transparent" onClick={handleCreatePost}>
                  <span style={{ color: 'grey', fontSize: '2.5rem' }}>+</span>
                </button>
              </div>
              <div className="row">
                <div>
                  {posts.length === 0 ? (
                    <div className="card-body" style={{ border: '2px solid lightgrey', borderRadius: '10px', display: 'flex', gap: '5px', flexWrap: 'wrap' }}>
                      <div style={{ margin: 'auto',  width: '100%', height: '100%'}}>
                        <div className="m-3" style={{ color:'grey', textAlign: 'center' }}>No Posts yet, but there's tons of potential</div>
                      </div>
                    </div>
                    ) : (
                  <div className="card-body" style={{ border: '2px solid lightgrey', borderRadius: '10px', display: 'flex', gap: '5px', flexWrap: 'wrap' }}>
                    {posts.map((post) => (
                      <div key={post.id} style={{ display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center', flexBasis: '24%' }}>
                        <button style={{ backgroundColor: '#f6f4f5' }} className="btn btn-light" onClick={() => handlePostClick(post)}>
                          <img width="100%" className="rounded" src={"http://localhost:8080/post-photos/" + post.id + "/" + post.image_path} alt={placeholder} />
                        </button>
                      </div>
                    ))}
                  </div>
                  )}
                </div>
              </div>
            </div>
          </div>
        </div>
        )}
    </div>
    )
};

export default UserProfile;