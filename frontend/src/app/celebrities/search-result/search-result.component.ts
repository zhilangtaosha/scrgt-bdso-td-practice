import {Component, ComponentFactoryResolver, Input, ViewChild} from '@angular/core';
import {CelebrityDetails, SearchResults} from '../../models/models';
import {PlaceholderDirective} from '../../common/placeholder.directive';
import {BackendService} from '../../services/backend.service';
import {CelebrityComponent} from '../celebrity/celebrity.component';

@Component({
  selector: 'app-search-result',
  templateUrl: './search-result.component.html',
  styleUrls: ['./search-result.component.scss']
})
export class SearchResultComponent {

  celebDetails: CelebrityDetails;
  displayedCeleb: string;
  showCelebDetail = false;
  displayId = -1;

  @ViewChild(PlaceholderDirective, {static: false})
  celebrityDetailPlaceHolder: PlaceholderDirective;
  @Input() canEdit: boolean;
  @Input() searchResults: SearchResults;

  constructor( private backendService: BackendService ) {}

  showDetail(celebId: number, celebName: string) {

    if (this.displayId === celebId) {
      this.showCelebDetail = false;
      this.displayedCeleb = '';
      this.displayId = -1;
    } else {
      this.displayedCeleb = celebId + celebName;
      this.backendService.getCelebrityData(celebId)
        .subscribe( data => {
          this.celebDetails = data;
          console.log(this.celebDetails);
          this.showCelebDetail = true;
          this.displayId = celebId;
        });
    }
  }

}
