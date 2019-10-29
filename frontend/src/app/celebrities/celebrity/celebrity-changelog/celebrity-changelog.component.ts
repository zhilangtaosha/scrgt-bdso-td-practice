import { Component, OnInit, ChangeDetectorRef, OnDestroy } from '@angular/core';
import { MatDialog } from '@angular/material';
import { HistoryViewComponent } from './history-view/history-view.component';
import { CelebrityChangelogService } from './celebrity-changelog.service';
import { CelebrityChangelog } from 'src/app/models/models';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-celebrity-changelog',
  templateUrl: './celebrity-changelog.component.html',
  styleUrls: ['./celebrity-changelog.component.scss']
})
export class CelebrityChangelogComponent implements OnInit, OnDestroy {

  changeLogData: CelebrityChangelog[] = [];
  private unsubscribe$: Subject<boolean> = new Subject<boolean>();

  constructor(public dialog: MatDialog, private changelogService: CelebrityChangelogService, private ref: ChangeDetectorRef) { }

  ngOnInit() {
    this.changelogService.changeLog.pipe(
      takeUntil(this.unsubscribe$)
    )
    .subscribe(changelog => {
      this.changeLogData = changelog;
      this.ref.detectChanges();
    });
  }

  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.changelogService.updateChangelog([]);
    this.ref.detach();
  }

  openView(selectedRow): void{
    this.dialog.open(HistoryViewComponent, {
      hasBackdrop: true,
      panelClass: 'bdso-modal',
      data: selectedRow
    });

  }

}
