import React from "react";
import { CaretDownOutlined } from "@ant-design/icons";
import styled from "styled-components";
import useClickOutside from "Hooks/useClickOutside";

const CustomizedDropdown = (props) => {
  const { ref, isVisible, setIsVisible } = useClickOutside(false);
  const { title, itemList, onSelect } = props;

  return (
    <DropdownWrapper ref={ref}>
      <DropdownHeader onClick={() => setIsVisible(!isVisible)}>
        {title}
        <CaretDownOutlined style={{ marginLeft: "2px", paddingTop: "3px" }} />
      </DropdownHeader>
      {isVisible && (
        <ItemList>
          {itemList.map((item) => (
            <Item key={item} onClick={() => setIsVisible(!isVisible)}>
              {item}
            </Item>
          ))}
        </ItemList>
      )}
    </DropdownWrapper>
  );
};

export default CustomizedDropdown;

const DropdownWrapper = styled.div`
  position: relative;
  width: fit-content;
  :not(:last-child) {
    margin-right: 24px;
  }
`;

const DropdownHeader = styled.div`
  cursor: pointer;
  display: flex;
  align-items: center;
  font-size: 13px;
`;

const ItemList = styled.ul`
  position: absolute;
  left: 0;
  top: 24px;
  padding: 4px 0;
  min-width: 100px;
  background: #fff;
  box-shadow: 0 3px 6px -4px rgba(0, 0, 0, 0.12), 0 6px 16px 0 rgba(0, 0, 0, 0.08),
    0 9px 28px 8px rgba(0, 0, 0, 0.05);
  z-index: 20;
`;

const Item = styled.li`
  cursor: pointer;
  padding: 5px 12px;
  line-height: 22px;
  transition: all 0.3s;
  :hover {
    background: #f5f5f5;
  }
`;
