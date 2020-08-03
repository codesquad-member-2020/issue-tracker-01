import React from "react";
import styled from "styled-components";
import { CheckOutlined } from "@ant-design/icons";

const LabelListItem = ({ props }) => {
  const { id, title, color, description, selected } = props;
  return (
    <Item className="item" data-index={id}>
      <CheckOutlined style={{ marginRight: "10px", visibility: selected ? "visible" : "hidden" }} />
      <LabelWrapper>
        <TitleWrapper>
          <Color color={color} />
          <Title>{title}</Title>
        </TitleWrapper>
        <Description>{description}</Description>
      </LabelWrapper>
    </Item>
  );
};

export default LabelListItem;

const Item = styled.li`
  display: flex;
  align-items: center;
`;

const Color = styled.div`
  display: inline-block;
  width: 14px;
  height: 14px;
  border-radius: 50%;
  margin-right: 7px;
  background: ${({ color }) => color};
`;

const LabelWrapper = styled.div`
  width: 90%;
`;

const TitleWrapper = styled.div`
  display: flex;
  align-items: center;
  height: 14px;
`;

const Title = styled.span`
  font-weight: 600;
  width: 90%;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
`;

const Description = styled.div`
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
`;
