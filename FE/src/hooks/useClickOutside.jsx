import { useState, useEffect, useRef } from "react";

const useClickOutside = ({ initialIsVisibility, closingCb }) => {
  const [isVisible, setIsVisible] = useState(initialIsVisibility);

  const ref = useRef(null);

  const handleClickOutside = (event) => {
    if (ref.current && !ref.current.contains(event.target)) {
      if (closingCb) closingCb();
      setIsVisible(false);
    }
  };

  useEffect(() => {
    document.addEventListener("click", handleClickOutside, true);
    return () => {
      document.removeEventListener("click", handleClickOutside, true);
    };
  });

  return { ref, isVisible, setIsVisible };
};

export default useClickOutside;
