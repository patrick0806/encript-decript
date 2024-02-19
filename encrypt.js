const crypto = require("crypto");

const ALGORITHM = "aes-256-cbc";
const KEY = Buffer.from("REtgV24bDB7xQYoMuypiBASMEaJbc59n", "utf8");
const IV = Buffer.from("8d2bc3f0f69426fc", "utf8");

function encrypt(data) {
  const cipher = crypto.createCipheriv(ALGORITHM, KEY, IV);
  let encrypted = cipher.update(data, "utf8", "base64");
  encrypted += cipher.final("base64");
  return encrypted;
}

function decrypt(encryptedData) {
  const decipher = crypto.createDecipheriv(ALGORITHM, KEY, IV);
  let decrypted = decipher.update(encryptedData, "base64", "utf8");
  decrypted += decipher.final("utf8");
  return decrypted;
}

const data = "Fui gerado no node";
console.log(`Data=["${data}"]`);

try {
  const encryptedData = encrypt(data);
  console.log(`Data encrypted=["${encryptedData}"]`);

  const decryptedData = decrypt("5yqfSGeL/JinYdm4gsNf4Dl6kXSx4Of0O/LDUqjU0Tc=");
  console.log(`Data decrypted=["${decryptedData}"]`);
} catch (ex) {
  console.error("Failed to encrypt or decrypt data");
}
