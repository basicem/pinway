import API from "./api";

export const getCollection = async (id) => {
  const response = await API.get(`/api/collections/${id}`, { headers: { Authorization: `Bearer ${localStorage.getItem('Bearer')}` } });
  return response.data;
};

export const getPostsForCollection = async (id) => {
  const response = await API.get(`/api/collections/${id}/posts`, { headers: { Authorization: `Bearer ${localStorage.getItem('Bearer')}` } }); 
  return response.data;
};

export const deleteCollection = async (id) => {
    const response = await API.delete(`/api/collections/${id}`, { headers: { Authorization: `Bearer ${localStorage.getItem('Bearer')}` } });
    return response.data;
};

export const postCollection = async (data) => {
  console.log("Collection is ", data);
  const response = await API.post(`/api/collections`, data ,{ headers: { Authorization: `Bearer ${localStorage.getItem('Bearer')}` } });
  return response.data;
};

export const getCollectionsForUser = async (id) => {
  const response = await API.get('/api/collections/user/' + id ,{ headers: { Authorization: `Bearer ${localStorage.getItem('Bearer')}` } });
  //console.log("Data: ", response.data);
  return response.data;
};

export const addPostToCollection = async (id, data) => {
  const response = await API.post('/api/collections/' + id + '/posts', data, { headers: { Authorization: `Bearer ${localStorage.getItem('Bearer')}` } })
  return response.data
};