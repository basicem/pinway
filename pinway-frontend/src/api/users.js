import API from "./api";

export const getCollectionsForUser = async (id) => {
  const response = await API.get(`/api/users/${id}/collection`);
  //const response = await API.get(`/api/users/${id}/collection`);
  //console.log("Post for user are: ");
  //console.log(response.data);
  return response.data;
};

export const getVisibleCollectionsForUser = async (data) => {
  //console.log("This is data: ", data);
  const response = await API.post(`/api/users/collection`, data, { headers: { Authorization: `Bearer ${localStorage.getItem('Bearer')}` } });
  // console.log("This is response.data: ", response.data);
  return response.data;
}

export const login = async (data) => {
  const response = await API.post(`/api/signin/login`, data);
  return response.data;
};

export const addUserToCollection = async (id, data) => {
  console.log(data);
  const response = await API.post(`/api/users/${id}/collection`, data, { headers: { Authorization: `Bearer ${localStorage.getItem('Bearer')}` } });
  return response.data;
};

export const getUserById = async (id) => {
  const response = await API.get(`/api/users/${id}`, { headers: { Authorization: `Bearer ${localStorage.getItem('Bearer')}` } });
  //console.log(response.data);
  return response.data;
};

export const addUSer = async(data) => {
  console.log("Data je: ", data);
  const response = await API.post('/api/users', data);
  return response.data;
}

export const updateUser = async(id,data) => {
  const response = await API.put(`/api/users/${id}`, data, { headers: { Authorization: `Bearer ${localStorage.getItem('Bearer')}` }, 'Content-Type' : `application/json`} )
  return response.data;
}

export const addUserPhoto = async(image, data) => {
  const form = new FormData();
  form.append('userDTO', JSON.stringify(data));
  form.append('image', image[0]);

  const response = await API.post(`/api/users/addPhoto`,
  form,
  { headers: { Authorization: `Bearer ${localStorage.getItem('Bearer')}`, 'Content-Type' : `multipart/form-data`  } });
  return response.data;
}

//followers

export const addFollowerForUser = async(id, data) => {
  const response = await API.post(`/api/users/${id}/followers`, data, { headers: { Authorization: `Bearer ${localStorage.getItem('Bearer')}` },  'Content-Type' : `application/json`  } )
  return response.data
}

export const getFollowersForUser = async (id) => {
  const response = await API.get(`/api/users/${id}/followers`, { headers: { Authorization: `Bearer ${localStorage.getItem('Bearer')}` },  'Content-Type' : `application/json`  });
  console.log(response.data);
  
  return response.data;
};
