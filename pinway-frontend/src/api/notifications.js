import API from "./api";

export const getNotificationsForUser = async (id) => {
  const response = await API.get(`/api/notifications/user/${id}`, { headers: { Authorization: `Bearer ${localStorage.getItem('Bearer')}` } });
  return response.data;
};

export const updateNotification = async (id, open) => {
  let data = {
    "id": id,
    "open": open
  }
  const response = await API.patch(`/api/notifications`, data, { headers: { Authorization: `Bearer ${localStorage.getItem('Bearer')}` } });
  return response.data;
}
