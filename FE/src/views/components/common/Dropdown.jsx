import React from "react";
import { Menu, Dropdown, Button } from "antd";
import Icon from "@ant-design/icons";

const MyDropdown = (props) => {
  const { type, title, items, onClickHandler } = props;

  const itemList = (
    <Menu onClick={onClickHandler}>
      {items.length !== 0 &&
        items.map(({ action, value }) => <Menu.Item key={`${action}_${value}`}>{value}</Menu.Item>)}
    </Menu>
  );

  return (
    <Dropdown overlay={itemList} trigger={["click"]} onClick={(e) => e.preventDefault()}>
      {type === "button" ? (
        <Button>
          {title}
          <DownOutlined />
        </Button>
      ) : (
        <a>
          {title}
          <DownOutlined style={{ marginLeft: "5px" }} />
        </a>
      )}
    </Dropdown>
  );
};

const DownSvg = () => (
  <svg width="0.7em" height="0.7em" fill="currentColor" viewBox="64 64 896 896" aria-hidden="true">
    <path d="M884 256h-75c-5.1 0-9.9 2.5-12.9 6.6L512 654.2 227.9 262.6c-3-4.1-7.8-6.6-12.9-6.6h-75c-6.5 0-10.3 7.4-6.5 12.7l352.6 486.1c12.8 17.6 39 17.6 51.7 0l352.6-486.1c3.9-5.3.1-12.7-6.4-12.7z"></path>
  </svg>
);

const DownOutlined = (props) => <Icon component={DownSvg} {...props} />;

export default MyDropdown;
