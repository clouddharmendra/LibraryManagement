import { Component, OnInit, OnDestroy } from '@angular/core';
import * as appProperties from 'src/app/app.properties';
import { Book } from 'src/app/models/books.model';
import { SelectedBooks } from 'src/app/models/selected-books.model';
import { Router, ActivatedRoute } from '@angular/router';
import { User } from 'src/app/models/user.model';
import { OnlineLibraryMgmtService } from 'src/app/services/online-library-mgmt.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit, OnDestroy {
  public userInfo: User;
  public books: Book[] = [];
  public booksMsterData: Book[];
  public searchText: string;
  public selectedBooks: SelectedBooks;
  userSubscription$: Subscription;
  allBooksSubscription$: Subscription;
  selBooksSubscription$: Subscription;
  constructor(public onlineLibraryMgmtService: OnlineLibraryMgmtService, public router: Router, private route: ActivatedRoute) { }

  ngOnInit() {
    this.userSubscription$ = this.onlineLibraryMgmtService.userData$.subscribe(user => {
      this.userInfo = user;
      if (null === user || null === user.emailId) {
        this.router.navigateByUrl(appProperties.URL_WLCM);
      } else {
        this.allBooksSubscription$ = this.onlineLibraryMgmtService.getAllBooks(user.emailId).subscribe((allBooks: Array<Book>) => {
          this.selBooksSubscription$ = this.onlineLibraryMgmtService.getSelectedBooksInfo(user.emailId).subscribe((selectedBooks) => {
            if (null === this.selectedBooks) {
              this.selectedBooks = new SelectedBooks();
            } else {
              this.selectedBooks = selectedBooks;
              this.selectedBooks.myBooks.forEach((myBook: Book) => {
                allBooks.forEach((aBook: Book) => {
                  if (aBook.id === myBook.id) {
                    aBook.available = false;
                    aBook.btnText = myBook.btnText;
                  }
                  this.books.push(aBook);
                });
              });
            }
          }, error => {
            this.booksMsterData = this.books = allBooks;
          });
          this.booksMsterData = this.books;
        });
      }
    });
  }

  // To Filter the cloths based on given search criteria.
  searchDetails() {
    this.books = JSON.parse(JSON.stringify(this.booksMsterData));
    if (null !== this.searchText && '' !== this.searchText.trim()) {
      let searchValue = this.searchText.trim().toLocaleLowerCase() ? this.searchText.trim().toLocaleLowerCase() : this.searchText.trim();
      this.books = [];
      for (var l = 0; l < this.booksMsterData.length; l++) {
        let title = this.booksMsterData[l].title.trim().toLocaleLowerCase();
        if (title.includes(searchValue)) {
          this.books.push(this.booksMsterData[l]);
        }
      }
    }
  }

  advancedSearch() {
    this.router.navigateByUrl(appProperties.URL_ADVSERCH);
  }

  // To show Book Details in new window
  onClick(book: Book) {
    this.router.navigate(['/book'], { queryParams: { 'book': JSON.stringify(book) } });
  }

  barrowBook(book: Book) {
    book.available = false;
    book.emailId = this.userInfo.emailId;
    book.btnText = 'Not Available';
    book.borrowedDate = new Date();
    if (this.selectedBooks && this.selectedBooks.myBooks.length > 0
      && null !== this.selectedBooks.emailId) {
      if (!this.selectedBooks.myBooks.find((bk) => { bk.id === book.id })) {
        this.selectedBooks.myBooks.push(book);
      }
    } else {
      let sb = new SelectedBooks();
      sb.myBooks.push(book);
      this.selectedBooks = sb;
    }
    this.selectedBooks.emailId = this.userInfo.emailId;
    this.onlineLibraryMgmtService.updateMyBooks(this.selectedBooks).subscribe((response: SelectedBooks) => {
      this.selectedBooks = response;
    }, (error: any) => {
      console.error('Error while updating books :::=> ', error)
    });
  }
  ngOnDestroy() {
    if (this.userSubscription$) {
      this.userSubscription$.unsubscribe();
    }
    if (this.allBooksSubscription$) {
      this.allBooksSubscription$.unsubscribe();
    }
    if (this.selBooksSubscription$) {
      this.selBooksSubscription$.unsubscribe();
    }
  }
}