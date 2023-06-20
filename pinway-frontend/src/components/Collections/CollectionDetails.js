import React, { useEffect, useState } from "react";
import { useNavigate, useLocation  } from "react-router-dom";


import { getPostsForCollection } from "api/collections";
import Loader from "components/Loader";
import placeholder from  "images/place_holder.png";


const CollectionDetails = () => {

    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(false);

    const [data, setData] = useState();
    const [posts, setPosts] = useState();

    const location = useLocation();
    const { id } = location.state;

    const getRandomHeight = () => Math.floor(Math.random() * 31) + 190;


    useEffect(() => {
        const fetch = async () => {
          try {
            setLoading(true);
            const response = await getPostsForCollection(id);

            setData(response.collectionDTO);
            setPosts(response.postDTO);
          } catch (e) {
            setError("Unable to fetch collection!");
          } finally {
            setLoading(false);
          }
        };
    
        fetch();
      }, [id]);

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
        <div className="container" style={{padding: '20px'}}>
          <Loader isLoading={loading} />
          {posts && (
            <div className="row">
              <div className="col-md-3 h-25">
                <div className="row">
                  <div className="container rounded offset-2 col-8 p-4" style={{ backgroundColor: '#d7a8f5' }}>
                    <div className="row">
                      <div className="col-md-12 text-center mb-3">
                        <button className="btn btn-light">{data.name}</button>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div className="col-md-9" style={{ border: '2px solid lightgrey', borderRadius: '10px', padding: "15px" }}>
                  {posts.map((post) => (
                      <img key={post.id} width="180" style={{ height: getRandomHeight(), margin: '10px' }} className = "rounded" src={"http://localhost:8080/post-photos/" + post.id + "/" + post.image_path} alt={placeholder} ></img>
                  ))}
              </div>
            </div>
          )}
        </div>
    )
};

export default CollectionDetails;