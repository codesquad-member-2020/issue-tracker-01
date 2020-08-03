import React from "react";

export const InlineCodeBlock = (props) => (
  <span
    style={{
      background: "#FAFBFC",
      border: "1px solid #d3d3d3",
      borderRadius: "5px",
      color: "#FF4500",
      padding: "3px 5px",
    }}
  >
    {props.value}
  </span>
);

export const CodeBlock = (props) => (
  <pre
    style={{
      background: "#000",
      color: "#fff",
      borderRadius: "5px",
      padding: "10px",
      fontFamily: "Consolas",
      fontSize: "14px",
    }}
  >
    <code>{props.value}</code>
  </pre>
);

export const BlockQuote = (props) => (
  <div style={{ fontWeight: "bold", color: "lightgray" }}>{props.children}</div>
);

export const LinkRenderer = (props) => (
  <a href={props.href} target="_blank">
    {props.children}
  </a>
);
