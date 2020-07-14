import React from "react";
import { Radio } from "antd";

const NavButtons = ({ menus }) => {
  const options = menus.map((menu) => ({ label: menu, value: menu }));

  return <Radio.Group options={options} optionType="button" buttonStyle="solid" />;
};

export default NavButtons;
