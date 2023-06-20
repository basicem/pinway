import React, { useEffect, useState } from "react";
import { useNavigate  } from "react-router-dom";
import 'components/Scrollbar.css';

import Loader from "components/Loader";
import { getHashTags, getFilteredPosts } from "api/posts";
import { useStore } from "./StoreContext";


const Root = () => {

    const {search: globalSearch, setSearch: setGlobalSearch} = useStore();

    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(true);

    const {search} = useStore();
    const navigate = useNavigate();

    const [hashtags, setHashtag] = useState(null);
    const [posts, setPosts] = useState([]);

    const colors = ['#6e8744', '#864b61', '#d2c4b9', '#b47262', '#73919d', "#c2bccd", "#995971", "#8d7a58"];

    useEffect(() => {
        if (search !== null && search !== undefined) {
            console.log('Global search value', search);
        }
        const fetch = async () => {
            try {
              const response = await getHashTags();
              const responsePosts = await getFilteredPosts(search);
              setHashtag(response);
              setPosts(responsePosts);
            } catch (e) {
              setError("Unable to Find Home Page!");
            } finally {
              setLoading(false);
            }
          };
      
          fetch();
    }, [search]);


    const getRandomColor = () => {
        const randomIndex = Math.floor(Math.random() * colors.length);
        return colors[randomIndex];
      };

    const handleItemClick = async (item) => {
        // Handle item click event
        console.log(`Clicked ${item.name}`);
        setGlobalSearch(item.name)
    };

    const handlePostClick = async (item) => {
      // Handle item click event
      console.log(`Clicked ${item.id}`);
      const id = item.id;
      navigate("/posts/details", { state: { id } });
    };


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

<div>
  <Loader isLoading={loading} />
  {/* hashtags */}
  {hashtags && (
    <div className="container-fluid custom-scrollbar" style={{ borderBottom: '1px solid lightgrey', overflowX: 'auto', overflowY: 'hidden', display: 'flex', alignItems: 'flex-end' }}>
    <div className="d-flex flex-nowrap">
      <p style={{ color: 'grey', marginRight: '10px', marginBottom: '10px', fontSize: '1.5rem', alignSelf: 'center' }}>Popular</p>
      {hashtags.map((hashtag, index) => (
        <button
          type="button"
          className="btn"
          key={hashtag.id}
          style={{ color: 'white', marginRight: '10px', marginBottom: '10px', backgroundColor: getRandomColor() }}
          onClick={() => handleItemClick(hashtag)}
        >
          {hashtag.name}
        </button>
      ))}
    </div>
  </div>
  
  
  
  
  )}

  {/* posts */}
  <div className="col-md-12" style={{ display: 'flex', flexWrap: 'wrap', width: '100%', justifyContent: 'flex-start' }}>
    {posts && posts.map((post) => (
      <img
        key={post.postDTO.id}
        width="260"
        style={{ margin: '10px' }}
        className="rounded"
        src={"http://localhost:8080/post-photos/" + post.postDTO.id + "/" + post.postDTO.image_path}
        alt=""
        onClick={() => handlePostClick(post.postDTO)}
      ></img>
    ))}
  </div>
</div>





    )
};

export default Root;