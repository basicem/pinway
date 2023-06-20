import React, { useEffect, useState } from 'react';

const CollectionCreateModal = ({ visible, handleClick, handleCreateCollection }) => {
  const [collectionName, setCollectionName] = useState('');
  const [isPrivate, setIsPrivate] = useState(false);
  const [nameError, setNameError] = useState('');

  const onApply = () => {
    if (collectionName.trim() === '') {
      setNameError('Collection name cannot be empty');
      return;
    }

    if (collectionName.length > 20) {
      setNameError('Collection name cannot exceed 20 characters');
      return;
    }

    handleCreateCollection(collectionName, isPrivate);
    setCollectionName('');
    setIsPrivate(false);
  };

  const handleCloseModal = () => {
    setNameError('');
    setCollectionName('');
    setIsPrivate(false);
    handleClick();
  };


  return (
    <div>
    <div className={`modal${visible ? ' show' : ''}`} tabIndex="-1" role="dialog" style={{ display: visible ? 'block' : 'none' }}>
      <div className="modal-dialog" role="document">
        <div className="modal-content">
          <div className="modal-header d-flex justify-content-center" style={{ borderBottom: 'none' }}>
            <h3 className="modal-title my-4" style={{ color: 'black' }}>Create Collection</h3>
          </div>
          <div className="modal-body">
            <div>
              <label htmlFor="collection-name">Name:</label>
              <input
                type="text"
                className="form-control"
                id="collection-name"
                value={collectionName}
                onChange={(e) => setCollectionName(e.target.value)}
                placeholder="Like Places to Go or Recipes to Make"
                style={{ marginBottom: '30px' }}
              />
              {nameError && <p style={{ color: 'red', fontSize: '12px', marginTop: '5px' }}>{nameError}</p>}
            </div>

            <div className="mb-6">
              <label htmlFor="collection-private" style={{ marginLeft: '10px' }}>
                <input
                  type="checkbox"
                  id="collection-private"
                  checked={isPrivate}
                  onChange={(e) => setIsPrivate(e.target.checked)}
                />
                <span style={{ marginLeft: '5px' }}>Keep this collection secret</span>
              </label>
            </div>
          </div>
          <div className="modal-footer">
            <button type="button" className="btn btn-secondary" onClick={handleCloseModal}>
              Close
            </button>
            <button type="button" className="btn" style={{ color: '#fff', backgroundColor: '#d7a8f5' }} onClick={onApply}>
              Create
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
  );
};

export default CollectionCreateModal;