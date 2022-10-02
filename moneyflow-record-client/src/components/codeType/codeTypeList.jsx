import React from "react";

function CodeType({ codeType, onRemove, onModify }) {
  return (
    <div>
      <h2>
        {codeType.id} : {codeType.name}
      </h2>
      <button onClick={() => onModify(codeType.id)}>Modify</button>
      <button onClick={() => onRemove(codeType.id)}>Delete</button>
    </div>
  );
}

function CodeTypeList({ codeTypes, onRemove, onModify }) {
  return (
    <>
      <h1>Code Type List</h1>
      <div>
        {codeTypes.map((codeType) => (
          <CodeType
            codeType={codeType}
            key={codeType.id}
            onRemove={onRemove}
            onModify={onModify}
          />
        ))}
      </div>
    </>
  );
}

export default CodeTypeList;
