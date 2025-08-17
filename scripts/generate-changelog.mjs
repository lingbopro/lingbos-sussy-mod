#!/usr/bin/env node

import fsp from 'fs/promises';
import path from 'path';
import { fileURLToPath } from 'url';

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

const version = process.env.VER || process.argv[2];

if (!version) {
  console.error('ERROR: Missing version');
  process.exit(2);
}

(async () => {
  const changelogPath = path.join(__dirname, '..', 'CHANGELOG.md');
  const changelog = await fsp.readFile(changelogPath, 'utf-8');
  const lines = changelog.split(/\r?\n/);

  let vers = {};
  let currentBigVer = null;
  let currentVer = null;
  for (const line of lines) {
    if (line.trim() === '') continue;
    // console.debug({ line, currentBigVer, currentVer });
    if (line.startsWith('## ')) {
      currentBigVer = line.slice(3).trim();
      currentVer = null;
      vers[currentBigVer] = { text: '', sub: [] };
      continue;
    } else if (!currentBigVer) continue;
    if (line.startsWith('### ')) {
      currentVer = line.slice(4).trim();
      vers[currentBigVer].sub.push({ name: currentVer, text: '' });
      continue;
    } else {
      if (currentVer) {
        vers[currentBigVer].sub[vers[currentBigVer].sub.length - 1].text += line + '\n';
      } else {
        vers[currentBigVer].text += line + '\n';
      }
    }
  }
  // console.debug(vers);
  if (vers[version]) {
    console.log(`# ${version}\n`);
    console.log(`${vers[version].text}\n`);
    for (const sub of vers[version].sub) {
      console.log(`## ${sub.name}\n\n${sub.text}\n`);
    }
  } else {
    let sub = null;
    for (const v in vers) {
      const current = vers[v];
      for (const s of current.sub) {
        if (s.name === version) {
          sub = s;
          break;
        }
      }
      if (sub) break;
    }
    if (sub) {
      console.log(`# ${sub.name}\n`);
      console.log(`${sub.text}\n`);
    } else {
      console.error(`ERROR: Version ${version} not found in CHANGELOG.md`);
      process.exit(1);
    }
  }
})();
