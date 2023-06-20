import { createContext, useState, useContext } from 'react';

const StoreContext = createContext();

export function StoreProvider({ children }) {
    const [search, setSearch] = useState();

    const value = {
        search,
        setSearch,
    }
  
    return (
      <StoreContext.Provider value={value}>
          {children}
      </StoreContext.Provider>
    );
  }

  export function useStore() {
    const context = useContext(StoreContext)
    if (context === undefined) {
        throw new Error('useStore must be used within a StoreProvider')
    }
    return context
  }

  export default StoreContext;