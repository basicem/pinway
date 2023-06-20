import React, { useEffect, useState } from "react";
import { useNavigate, useLocation } from "react-router-dom";

import { getVisibleCollectionsForUser, getFollowersForUser, getUserById, addFollowerForUser } from "api/users";
import { getPostsForUser } from "api/posts";
import { toast } from "react-toastify";

import monkey from  "images/monkey.png";
import collections_place_holder from "images/collections_place_holder.jpg";
import placeholder from  "images/place_holder.png";

import Loader from "components/Loader";

const UserDetails = () => {

    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    const [collections, setCollections] = useState();
    const [posts, setPosts] = useState();
    const [followers, setFollowers] = useState();
    const [user, setUser] = useState();

    const navigate = useNavigate();
    const location = useLocation();
    const { id } = location.state;

    const [isVisible, setIsVisible] = useState(false);

    useEffect(() => {
        const fetch = async () => {
          try {
            setLoading(true);
            const data = {
              "userId": id,
              "actionUserId": parseInt(localStorage.getItem("UserId"))
            };
            const responseUser = await getUserById(id);
            const responseCollections = await getVisibleCollectionsForUser(data);

            const responsePosts = await getPostsForUser(id);
            const responseFollowers = await getFollowersForUser(id);

            setUser(responseUser);
            setCollections(responseCollections);
            setPosts(responsePosts);
            setFollowers(responseFollowers);
            
            
            const containsId = responseFollowers.some(obj => obj.id === parseInt(localStorage.getItem("UserId")));
            if(containsId !== true)
              setIsVisible(true);

            setLoading(false);
          } catch (e) {
            setError("Unable to fetch User Details!");
          } finally {
            setLoading(false);
          }
        };
    
        fetch();
      }, [id]);

      const handleItemClick = async (item) => {
        // Handle item click event
        //console.log(`Clicked ${item.id}`);
        const userId = user.id;
        navigate("/collections/user/details", { state: { item, userId } });
      };

      const handlePostClick = async (item) => {
        // Handle item click event
        console.log(`Clicked ${item.id}`);
        const id = item.id;
        navigate("/posts/details", { state: { id } });
      };

      const handleFollow = async(user) => {
        try {
          await addFollowerForUser(parseInt(localStorage.getItem("UserId")), {id: user.id})
          const responseFollowers = await getFollowersForUser(id);
          setIsVisible(false);
          setFollowers(responseFollowers);
          toast.success("User followed!");
        }
        catch(err) {
          toast.error("Unable to follow user!");
        }
      };
    

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
        <Loader isLoading={loading} />
        {user && posts && collections && (
          <div>
        {/* user  */}
          <div className="offset-1 col-md-9 text-center mx-auto">
            <img className="rounded-circle  img-responsive d-inline-block align-text-top" src={"http://localhost:8083/user-photos/" + user.id + "/" + user.image_path} alt='currentPhotoURL' style={{borderRadius:"50%", width:"150px", height:"150px",objectFit:"cover"}} />
            <div style={{ margin: '0.5rem 0' }}>
              <div style={{ fontSize: '1.2rem' }}>{user.name} {user.surname}</div>
              <div className="text-secondary">@{user.username}</div>
              <div style={{ fontSize: '0.9rem' }}>
                {followers.length} follower {user.following.length} following
              </div>
            </div>
            <button 
              className="btn rounded" 
              style={{ 
                backgroundColor: isVisible ? '#d7a8f8' : 'grey',
                color: 'white', 
                pointerEvents: isVisible ? 'auto' : 'none'
              }}
              onClick={() => {handleFollow(user)}}
            >
              {isVisible ? 'Follow' : 'Following'}
              </button>
          </div>

        {/* collections  */}
        <div className="offset-1 col-md-11 mx-auto mb-5 mt-3">
            <div className="card border-0">
              <div className="d-flex align-items-center justify-content-between">
                <div className="text-secondary m-2">Collections</div>
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

        {/* posts */}
        <div className="offset-1 col-md-11 mx-auto mb-5 mt-3">
            <div className="card border-0">
              <div className="d-flex align-items-center justify-content-between">
                <div className="text-secondary m-2">Posts</div>
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

export default UserDetails;


                        // <button style={{ backgroundColor: '#f6f4f5' }} className="btn btn-light" onClick={() => handleItemClick(collection)}>
                        //   <img width="90px" style={{ margin: '5px' }} className="rounded" src={collections_place_holder} alt={collection.name} />
                        //   <div className="text-secondary" style={{ width: '90px', textAlign: 'left' }}>{collection.name}</div>
                        // </button> 