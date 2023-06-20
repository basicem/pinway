import React, { useEffect, useState } from "react";
import { useNavigate, useLocation  } from "react-router-dom";


import { getPostsForCollection } from "api/collections";
import Loader from "components/Loader";
import placeholder from  "images/place_holder.png";
import {getUserById} from "api/users"

const CollectionOtherUser = () => {

    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(false);

    const [data, setData] = useState();
    const [posts, setPosts] = useState();
    const [user, setUser] = useState(null);

    const location = useLocation();
    const navigate = useNavigate();
    const { item, userId } = location.state;

    const getRandomHeight = () => Math.floor(Math.random() * 21) + 210;

    const handleProfile = () => {
      const id = userId;
      navigate("/users/details", { state: { id } });
    }

    const handlePostClick = async (item) => {
      // Handle item click event
      console.log(`Clicked ${item.id}`);
      const id = item.id;
      navigate("/posts/details", { state: { id } });
    };


    useEffect(() => {
        const fetch = async () => {
          try {
            setLoading(true);
            const responseUser = await getUserById(userId);
            setData(item.collectionDTO);
            setPosts(item.postDTO);
            setUser(responseUser);
          } catch (e) {
            setError("Unable to fetch collection!");
          } finally {
            setLoading(false);
          }
        };
    
        fetch();
      }, [item, userId]);

    if (error) {
      return (
        <div className="container offset-2 col-8" style={{padding: '15px'}}>
          <div className="alert alert-danger" role="alert">
            {error}
          </div>
        </div>
      );
    }

    return (
        <div className="container">
          <Loader isLoading={loading} />
          {posts && (
            <div className="row">
              <div className="col-3">
                <div className="row">
                  <div 
                    className="container offset-1 col-9 p-4" 
                    style={{ borderRadius: '30px', background: '#d7a8f5'}}
                  >
                  <div className="row">
                    <div className="col-md-12 text-center mb-3">
                      <button 
                        className="btn btn-light" 
                        style={{ 
                          backgroundColor: '#ffffff', 
                          //color: 'grey', 
                          borderRadius: '30px', 
                          padding: '8px 16px', 
                          fontSize: '18px',
                        }}
                      >
                        {data.name}
                      </button>
                      <div className="text-secondary" style={{fontSize: '14px'}}>{posts.length} Pins</div>
                    </div>

                    <div className="d-flex align-items-center">
                      <img 
                        className="rounded-circle me-2" 
                        style={{cursor: 'pointer'}}
                        src={"http://localhost:8083/user-photos/" + user.id + "/" + user.image_path}
                        alt={placeholder}
                        width="35" 
                        height="35" 
                        onClick={handleProfile}
                      />
                      <span 
                        style={{ fontSize: '16px', cursor: 'pointer' }}
                        onClick={handleProfile}
                      >
                        {user.name} {user.surname}
                        <div className="text-secondary" style={{fontSize: '14px'}}>@{user.username}</div>
                      </span>
                      
                    </div>

                  </div>
                </div>


                </div>
              </div>
              
              <div className="col-9" style={{ border: '2px solid lightgrey', borderRadius: '10px', padding: '15px', textAlign: 'center' }}>
              {posts.length === 0 ? (
                <div style={{ margin: 'auto',  width: '100%', height: '100%'}}>
                  <div style={{ color:'grey', textAlign: 'center' }}>There aren't any Pins on this board yet.</div>
                </div>
              ) : (
                <div  style={{display: "flex",
                "flex-wrap": "wrap",
                "justify-content": "flex-start",
                "align-items": "center",
                marginLeft: "7.5%"}}
                >
                  {posts.map((post) => (
                    <img
                      key={post.id}
                      width="195"
                      style={{ height: getRandomHeight(), margin: '5px' }}
                      onClick={() => handlePostClick(post)}
                      className="rounded"
                      src={'http://localhost:8080/post-photos/' + post.id + '/' + post.image_path}
                      alt={placeholder}
                    />
                  ))}
                </div>
              )}
            </div>
            </div>
          )}
        </div>
    )
};

export default CollectionOtherUser;