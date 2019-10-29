import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material';
import { CelebrityChangelog } from 'src/app/models/models';

@Component({
  selector: 'app-history-view',
  templateUrl: './history-view.component.html',
  styleUrls: ['./history-view.component.scss']
})
export class HistoryViewComponent implements OnInit {

  orig = '';
  update = '';

  constructor(@Inject(MAT_DIALOG_DATA) public data: CelebrityChangelog) { }

  ngOnInit() {
    if (!this.data.changes.oldValue) {
      this.update = this.highlight(this.data.changes.newValue);
    } else {
      this.diffString(this.data.changes.oldValue, this.data.changes.newValue);
    }
  }

  diffString(o: string, n: string) {
    o = o.replace(/\s+$/, '');
    n = n.replace(/\s+$/, '');

    const out = this.diff(o === '' ? [] : o.split(/\s+/), n === '' ? [] : n.split(/\s+/));

    for (const i of out.n) {
      if (i.text == null) {
        this.update += this.highlight(i);
      } else {
        this.update +=  i.text + ' ';
      }
    }

    for (const k of out.o) {
      if (k.text == null) {
        this.orig += this.highlight(k);
      } else {
        this.orig += k.text + ' ';
      }
    }
  }

  diff(o, n) {
    const ns = new Object();
    const os = new Object();

    for ( let l = 0; l < n.length; l++ ) {
      if ( ns[ n[l] ] == null ) {
        ns[ n[l] ] = { rows: new Array(), o: null };
      }
      ns[ n[l] ].rows.push( l );
    }

    for ( let p = 0; p < o.length; p++ ) {
      if ( os[ o[p] ] == null ) {
        os[ o[p] ] = { rows: new Array(), n: null };
      }
      os[ o[p] ].rows.push( p );
    }

    for (const prop in ns) {
      if (ns[prop].rows.length === 1 && typeof(os[prop]) !== 'undefined' && os[prop].rows.length === 1) {
        n[ns[prop].rows[0]] = {text: n[ns[prop].rows[0]], row: os[prop].rows[0]};
        o[os[prop].rows[0]] = {text: o[os[prop].rows[0]], row: ns[prop].rows[0]};
      }
    }

    for (let j = 0; j < n.length - 1; j++) {
      if (n[j].text != null && n[j + 1].text == null && n[j].row + 1 < o.length && o[n[j].row + 1].text == null
        && n[j + 1] === o[n[j].row + 1]) {
          n[j + 1] = {text: n[j + 1], row: n[j].row + 1};
          o[n[j].row + 1] = {text: o[n[j].row + 1], row: j + 1};
        }
    }

    for (let m = n.length - 1; m > 0; m--) {
      if ( n[m].text != null && n[m - 1].text == null && n[m].row > 0 && o[ n[m].row - 1 ].text == null &&
        n[m - 1] === o[ n[m].row - 1 ] ) {
          n[m - 1] = { text: n[m - 1], row: n[m].row - 1 };
          o[n[m].row - 1] = { text: o[n[m].row - 1], row: m - 1 };
      }
    }
    return {o, n};
  }

  highlight(text: string) {
    return '<span class="highlight">' + text + '</span> ';
  }

}
