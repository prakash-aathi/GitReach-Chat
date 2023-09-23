"use client";
import React, { useState } from "react";

const Compose = () => {
  const [message, setmessage] = useState("");
  const [subject, setsubject] = useState("");
  const [toIds, settoIds] = useState("");
  const [loading, setloading] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setloading(true);
    const msg = {
      toIds,
      subject,
      message,
      from: "prakash",
    };
    console.log(msg);
    const res = await fetch("http://localhost:8080/compose", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(msg),
    });

    if (res.status === 200) {
      setloading(false);
      setmessage("");
      setsubject("");
      settoIds("");
      alert("message sent");
    } else {
      setloading(false);
      alert("message not sent");
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <div className="mb-3">
        <label htmlFor="recipient-name" className="col-form-label">
          TO:
        </label>
        <input
          type="text"
          className="form-control"
          id="recipient-name"
          name="toIds"
          required
          onChange={(e) => settoIds(e.target.value)}
          value={toIds}
        />
      </div>
      <div className="mb-3">
        <label htmlFor="recipient-sub" className="col-form-label">
          Subject:
        </label>
        <input
          type="text"
          className="form-control"
          id="recipient-sub"
          name="subject"
          required
          onChange={(e) => setsubject(e.target.value)}
          value={subject}
        />
      </div>
      <div className="mb-3">
        <label htmlFor="message-text" className="col-form-label">
          Message:
        </label>
        <textarea
          className="form-control"
          id="message-text"
          name="message"
          required
          onChange={(e) => setmessage(e.target.value)}
          value={message}
        ></textarea>
      </div>
      <div className="modal-footer">
        <button
          type="button"
          className="btn btn-secondary"
          data-bs-dismiss="modal"
        >
          Close
        </button>
        <button type="submit" className="btn btn-primary" disabled={loading}>
          {!loading && <div> Send message</div>}
          {loading && (
            <div className="spinner-border spinner-border-sm" role="status">
              <span className="visually-hidden">Loading...</span>
            </div>
          )}
        </button>
      </div>
    </form>
  );
};

export default Compose;
