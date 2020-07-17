import React from "react";
import styled from "styled-components";
import Label from "@/common/Label";
import Button from "@/common/Button";

const LabelItem = (props) => {
  const { id, title, color, description } = props;

  return (
    <Wrapper>
      <LabelWrapper>
        <Label key={title} title={title} color={color} />
      </LabelWrapper>
      <Description>{description}</Description>
      <ButtonWrapper>
        <Button type="text" text="Edit" />
        <Button type="text" text="Delete" />
      </ButtonWrapper>
    </Wrapper>
  );
};

export default LabelItem;

const Wrapper = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 15px 12px;
  border-top: 1px solid #e1e4e8;
  font-size: 12px;
`;

const LabelWrapper = styled.div`
  width: 20%;
`;

const Description = styled.div`
  flex: 1;
`;

const ButtonWrapper = styled.div`
  display: flex;
`;
