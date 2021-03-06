import { Component, OnInit, Input } from '@angular/core';
import { Book } from 'src/app/models/books.model';
import { SelectedBooks } from 'src/app/models/selected-books.model';
import { User } from 'src/app/models/user.model';import { Router, ActivatedRoute } from '@angular/router';
import * as appProperties from 'src/app/app.properties'
import { OnlineLibraryMgmtService } from 'src/app/services/online-library-mgmt.service';

@Component({
  selector: 'app-book-details',
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.scss']
})
export class BookDetailsComponent implements OnInit {

  @Input('bookDetails') bookDetails: Book;
  userEmail: string;
  selectedBooks: SelectedBooks;

  constructor(private onlineLibraryMgmtService: OnlineLibraryMgmtService,
    private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.onlineLibraryMgmtService.userData$.subscribe((user: User) => {
      this.userEmail = user.emailId;
      if (null === user) {
          this.router.navigateByUrl(appProperties.URL_WLCM);
      } else {
        this.onlineLibraryMgmtService.getSelectedBooksInfo(this.userEmail).subscribe((response: SelectedBooks) => {
          this.selectedBooks = response;
        },error=>{
          // this.router.navigateByUrl(appProperties.URL_HOME);
        });
        let bookDetails = this.route.snapshot.queryParamMap.get('book');
        this.bookDetails = JSON.parse(bookDetails);
      }
    });
  }
  barrowBook() {
    if (undefined === this.selectedBooks || null === this.selectedBooks) {
      this.selectedBooks = new SelectedBooks();
      this.selectedBooks.myBooks = [];
    }
    this.bookDetails.available = false;
    this.bookDetails.btnText = 'Not Available';
    this.bookDetails.borrowedDate = new Date();
    this.selectedBooks.emailId = this.userEmail;
    this.selectedBooks.myBooks.push(this.bookDetails);
    this.onlineLibraryMgmtService.updateMyBooks(this.selectedBooks).subscribe(response => {
      this.router.navigateByUrl(appProperties.URL_HOME);
    });
  }
}
