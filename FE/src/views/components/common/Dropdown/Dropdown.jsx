import React, { useState } from "react";
import ReactDOM from "react-dom";
import styled from "styled-components";
import { SettingOutlined, CaretDownOutlined } from "@ant-design/icons";
import UserListItem from "./UserListItem";
import LabelListItem from "./LabelListItem";
import MilestonListItem from "./MilestonListItem";
import useClickOutside from "Hooks/useClickOutside";
import itemList from "Assets/mockItemList";

const components = {
  users: UserListItem,
  labels: LabelListItem,
  milestones: MilestonListItem,
};

const Dropdown = (props) => {
  const { type, category, title, openingCallback, closingCallback } = props;
  const [items, setItems] = useState(itemList[category]);
  const { ref, isVisible, setIsVisible } = useClickOutside({
    initialIsVisibility: false,
    closingCb: () => {
      if (items && closingCallback) closingCallback(items.filter((item) => item.selected));
    },
  });

  const Item = components[category];

  const openMenu = () => {
    setIsVisible(!isVisible);
    // fetch list
  };

  const toggleItem = (e) => {
    const targetItemIdx = ReactDOM.findDOMNode(e.target).closest(".item").dataset.index;
    const updatedList = items.map((item) =>
      item.id == targetItemIdx ? { ...item, selected: !item.selected } : item
    );
    setItems(updatedList);
  };

  return (
    <DropdownWrapper ref={ref}>
      <DropdownHeader onClick={openMenu}>
        <div>{title}</div>
        {type === "wide" ? (
          <SettingOutlined style={{ display: "inline-block" }} />
        ) : (
          <CaretDownOutlined style={{ marginLeft: "2px", paddingTop: "3px" }} />
        )}
      </DropdownHeader>
      {isVisible && (
        <ItemList type={type} onClick={toggleItem}>
          {items.map((item) => (
            <Item key={item.id} className="item" data-index={item.id} props={item} />
          ))}
        </ItemList>
      )}
    </DropdownWrapper>
  );
};

export default Dropdown;

const DropdownWrapper = styled.div`
  position: relative;
  width: 100%;
  :not(:last-child) {
    margin-right: 24px;
  }
`;

const DropdownHeader = styled.div`
  cursor: pointer;
  width: 100%;
  display: flex;
  justify-content: ${(props) => (props.type ? "flex-start" : "space-between")};
  align-items: center;
  font-size: 13px;
`;

const ItemList = styled.ul`
  position: absolute;
  left: 0;
  top: 24px;
  padding: 4px 0;
  width: 314px;
  max-height: 320px;
  overflow-y: auto;
  background: #fff;
  box-shadow: 0 3px 6px -4px rgba(0, 0, 0, 0.12), 0 6px 16px 0 rgba(0, 0, 0, 0.08),
    0 9px 28px 8px rgba(0, 0, 0, 0.05);
  z-index: 20;
  list-style: none;
`;
