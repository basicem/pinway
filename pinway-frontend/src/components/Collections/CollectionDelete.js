import React from 'react';

function CollectionDelete({ visible, handleClick, handleDelete }) {
  const handleCloseModal = () => {
    handleClick();
  };

  const onApply = () => {
    handleDelete();
  };

  return (
    <div>
      <div className={`modal${visible ? ' show' : ''}`} tabIndex="-1" role="dialog" style={{ display: visible ? 'block' : 'none' }}>
          <div className="modal-dialog" role="document">
            <div className="modal-content">
              <div className="modal-header">
                <h5 className="modal-title">Delete Collection</h5>
              </div>
              <div className="modal-body">
                <p>Are you sure you want to delete this Collection?</p>
              </div>
              <div className="modal-footer">
                <button type="button" className="btn btn-secondary" onClick={handleCloseModal}>
                  Close
                </button>
                <button type="button" className="btn btn-danger" onClick={onApply}>
                  Delete
                </button>
              </div>
            </div>
          </div>
        </div>
    </div>
  );
}



export default CollectionDelete;