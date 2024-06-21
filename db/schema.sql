CREATE TABLE users (
    userId TEXT PRIMARY KEY,
    pass TEXT
);

CREATE TABLE collection (
    colId INTEGER PRIMARY KEY,
    creatorId TEXT REFERENCES Users(userId),
    colName TEXT,
    access ENUM('pub', 'pvt', 'shared')
);

CREATE TABLE collection_users (
    userId TEXT REFERENCES users(userId),
    colId INTEGER REFERENCES collection(colId),
    PRIMARY KEY (userId, colId)
);

CREATE TABLE links (
    linkId INTEGER PRIMARY KEY,
    colId INTEGER REFERENCES collection(colId),
    linkName TEXT,
    linkUrl TEXT,
    date TEXT,
    type TEXT
);

CREATE TABLE category (
    category TEXT,
    linkId INTEGER REFERENCES links(linkId),
    PRIMARY KEY (category, linkId)
);

CREATE TABLE tags (
    tag TEXT,
    linkId INTEGER REFERENCES links(linkId)
);

