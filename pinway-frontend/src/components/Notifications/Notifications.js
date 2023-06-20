import React, { useEffect, useState } from 'react';
import { useNavigate  } from "react-router-dom";

import { getNotificationsForUser, updateNotification } from "api/notifications";

import pin from  "images/pin.svg";
import follow from "images/person-add.svg";


const Notifications = () => {
  const [notifications, setNotifications] = useState([]);
  const [read, setRead] = useState(true);
  const [click, setClick] = useState(0);

  const navigate = useNavigate();

  useEffect(() => {
    const fetchNotifications = async () => {
        try {
          const response = await getNotificationsForUser(localStorage.getItem("UserId"))
          setNotifications(response);
          if (response && response.length > 0) {
            setRead(false);
          }
        } catch (error) {
          console.error('Error fetching notifications:', error);
        }
      };

    fetchNotifications();

    const interval = setInterval(fetchNotifications, 120000);
    //const interval = setInterval(fetchNotifications, 60000);

    return () => clearInterval(interval);
  }, []);

    const handleRingButtonClick = () => {
        if(click === 0) {
            setClick(1);
            handleDropdownOpen();
        }
        else if(click === 1) {
            setClick(0);
            handleDropdownClose();
        }
    }

  const handleDropdownOpen = () => {
    try {
        let tempNotifications = notifications;
        tempNotifications.forEach((notification) => {
            // Perform the update API call for each notification
            updateNotification(notification.id, true);
        });
        setRead(true);
    } catch (error) {
        console.error('Error updating notifications:', error);
    }
    console.log('Dropdown opened');
  };

  const handleDropdownClose = () => {
    setNotifications([]);
    console.log('Dropdown closed');
  };

  const handleItemClick = async (item) => {
    // Handle item click event
    console.log('Clicked ', item);

    if(item.notificationType.id === 3) {
      const id = item.actionUserId
      console.log("id je: ", id);
      navigate("/users/details", { state: { id } });
    }
    else {
      const id = item.pinnedPost
      console.log("id je: ", id);
      navigate("/posts/details", { state: { id } });
    }
  };

  return (
    <div>
        <div className="dropdown">
            <button
                style={{ color: read ? 'grey' : '#864b61' }}
                className="btn border-0 rounded-circle d-flex align-items-center justify-content-center dropdown-toggle"
                type="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
                onClick={handleRingButtonClick}
            >
                <svg
                xmlns="http://www.w3.org/2000/svg"
                width="20"
                height="20"
                fill="currentColor"
                className="bi bi-bell-fill"
                viewBox="0 0 16 16"
                >
                <path d="M8 16a2 2 0 0 0 2-2H6a2 2 0 0 0 2 2zm.995-14.901a1 1 0 1 0-1.99 0A5.002 5.002 0 0 0 3 6c0 1.098-.5 6-2 7h14c-1.5-1-2-5.902-2-7 0-2.42-1.72-4.44-4.005-4.901z" />
                </svg>
            </button>
                <div className="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton">
                  {notifications.length === 0 ? (
                    <button
                    className="dropdown-item"
                    type="button"
                    >No unread notifications.
                    </button> 
                  ) : 
                  (
                    <div>
                    {notifications.map((notification) => (
                    <button
                        key={notification.id}
                        className="dropdown-item"
                        type="button"
                        onClick={() => handleItemClick(notification)}
                    >
                      <div style={{ display: 'flex', alignItems: 'center', gap: '5px', padding: '5px' }}>
                        {notification.notificationType.id === 3 ? (<img src={follow} alt='' />) : (<img src={pin} alt='' />)}
                        {notification.content}
                      </div>
                    </button>
                    ))}
                    </div>
                  )}
                </div>
        </div>
    </div>
  );
};

export default Notifications;


